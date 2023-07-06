package io.mboettger.bachelorthesis.domain.customer.address

@JvmInline
value class Street(val value: String) {
    init {
        check(value.isNotBlank()) { "Street should not be blank" }
        check(value.none { it.isDigit() }) { "Street should not contain digits" }
    }
}