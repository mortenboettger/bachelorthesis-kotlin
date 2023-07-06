package io.mboettger.bachelorthesis.domain.customer

@JvmInline
value class EmailAddress(val value: String) {
    init {
        check(value.isNotBlank()) { "E-Mail address should not be blank" }
        check(Regex("^[\\w\\-+.]+@(?>[a-zA-Z0-9\\-]+\\.)+(?>[a-zA-Z0-9]+)\$").matches(value)) { "E-Mail address malformed" }
    }
}
