package io.mboettger.bachelorthesis.domain.customer.address

@JvmInline
value class District(val value: String) {
    init {
        check(value.isNotBlank()) { "District should not be blank" }
        check(value.none { it.isDigit() }) { "District should not contain digits" }
    }
}
