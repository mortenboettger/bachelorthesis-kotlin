package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show

sealed class ShowCustomerRequest {

    data class ById(val customerId: String) : ShowCustomerRequest()

    data class ByEmailAddress(val emailAddress: String) : ShowCustomerRequest()
}