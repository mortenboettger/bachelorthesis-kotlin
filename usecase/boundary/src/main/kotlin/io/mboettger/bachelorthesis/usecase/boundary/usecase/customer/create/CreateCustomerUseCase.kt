package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create

import io.mboettger.bachelorthesis.usecase.boundary.InputBoundary

interface CreateCustomerUseCase : InputBoundary<CreateCustomerRequest, CreateCustomerResponse, CreateCustomerResponse.Error>