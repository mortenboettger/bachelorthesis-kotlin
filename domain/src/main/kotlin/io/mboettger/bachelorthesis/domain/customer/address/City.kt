package io.mboettger.bachelorthesis.domain.customer.address

@JvmInline
value class City(val value: String) {
    init {
        check(value.isNotBlank()) { "City should not be blank" }
        check(value.none { it.isDigit() }) { "City should not contain digits" }
    }
}
