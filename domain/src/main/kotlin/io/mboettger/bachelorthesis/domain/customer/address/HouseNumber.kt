package io.mboettger.bachelorthesis.domain.customer.address

@JvmInline
value class HouseNumber(val value: String) {
    init {
        check(value.isNotBlank()) { "House number should not be blank" }
        check(value.all { it.isDigit() }) {"House number should only contain digits"}
    }

}
