package io.mboettger.bachelorthesis.domain.customer

@JvmInline
value class FirstName(val value: String) {
    init {
        check(value.isNotBlank()) { "Firstname should not be blank" }
        check(value.first().isUpperCase()) { "First character of firstname must be uppercase" }
        check(value.length >= 2) { "Firstname must contain at least two characters" }
        check(Regex("^[a-zA-Z\\- ]+\$").matches(value)) { "Malformed firstname" }
    }
}
