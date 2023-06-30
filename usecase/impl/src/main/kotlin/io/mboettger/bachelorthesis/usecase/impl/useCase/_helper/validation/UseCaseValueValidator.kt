package io.mboettger.bachelorthesis.usecase.impl.useCase._helper.validation

internal class UseCaseValueValidator<T : Any?>(
    val name: String,
    val value: T,
    val replaceValidator: (old: UseCaseValueValidator<*>, new: UseCaseValueValidator<*>) -> Unit
) {
    val messages = mutableListOf<String>()

    infix fun shouldBe(other: T) = UseCaseValidatedValue(this) {
        this.value == other
    }

    infix fun shouldBe(callable: (T) -> Boolean) = UseCaseValidatedValue(this) {
        callable(value)
    }

    infix fun shouldHave(callable: (T) -> Boolean) = shouldBe(callable)

    infix fun should(callable: (T) -> Boolean) = shouldBe(callable)

    infix fun shouldNot(callable: (T) -> Boolean) = shouldBe {
        !callable(value)
    }

    infix fun <Q : Any?> with(callable: (T) -> Q) =
        UseCaseValueValidator(name, callable(value), replaceValidator).also {
            replaceValidator(this, it)
            it.messages.addAll(messages)
        }

    infix fun reject(message: String) {
        messages.add(message)
    }
}

internal infix fun <Q : CharSequence?> UseCaseValueValidator<Q>.shouldMatch(regex: Regex) =
    UseCaseValidatedValue(this) {
        this@shouldMatch.value != null && this@shouldMatch.value.matches(regex)
    }