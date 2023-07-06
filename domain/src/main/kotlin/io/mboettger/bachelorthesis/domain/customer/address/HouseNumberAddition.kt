package io.mboettger.bachelorthesis.domain.customer.address

@JvmInline
value class HouseNumberAddition(val value: String) {
    init {
        check(value.isNotBlank()) { "House number addition should not be blank" }
        check(Regex("^[\\w\\-+]+\$").matches(value)) { "Malformed house number addition" }
    }
}
