package io.mboettger.bachelorthesis.domain.customer

@JvmInline
value class LastName(val value: String) {
    init {
        check(value.isNotBlank()) { "Lastname should not be blank" }
        check(value.first().isUpperCase()) { "First character of lastname must be uppercase" }
        check(value.length >= 2) { "Lastname must contain at least two characters" }
        check(Regex("^[a-zA-Z- ]+\$").matches(value)) { "Malformed lastname" }
    }
}
