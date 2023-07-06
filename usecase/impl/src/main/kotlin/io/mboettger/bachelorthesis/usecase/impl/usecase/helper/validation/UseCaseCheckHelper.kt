package io.mboettger.bachelorthesis.usecase.impl.usecase.helper.validation

internal infix fun <T> T?.should(callable: (T) -> Boolean): Boolean = this?.let { callable(it) } ?: false

internal infix fun <T> T?.shouldNot(callable: (T) -> Boolean): Boolean = !should(callable)

internal infix fun <T : CharSequence> T?.shouldMatch(regex: Regex): Boolean = this?.matches(regex) ?: false

internal val beNull = { value: Any? -> value == null }

