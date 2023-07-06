package io.mboettger.bachelorthesis.domain.customer

@JvmInline
value class PhoneNumber(val value: String) {
    init {
        check(value.isNotBlank()) { "Phone-number should not be blank" }
        check(Regex("^\\+?[1-9][0-9]{7,}\$").matches(value)) { "Malformed phone-number" }
    }
}
