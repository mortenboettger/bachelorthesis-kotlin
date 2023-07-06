package io.mboettger.bachelorthesis.domain.customer.address

@JvmInline
value class PostCode(val value: String) {
    init {
        check(value.isNotBlank()) { "Post code should not be blank" }
        check(value.all { it.isDigit() }) { "Post code should only contain digits" }
        check(value.length == 5) { "Post code should only contain exactly 5 digits" }
    }
}
