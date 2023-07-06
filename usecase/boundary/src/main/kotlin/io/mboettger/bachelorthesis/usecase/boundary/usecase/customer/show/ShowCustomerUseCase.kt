package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show

import io.mboettger.bachelorthesis.usecase.boundary.InputBoundary

interface ShowCustomerUseCase : InputBoundary<ShowCustomerRequest, ShowCustomerResponse, ShowCustomerResponse.Error>