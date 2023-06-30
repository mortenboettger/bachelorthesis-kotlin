package io.mboettger.bachelorthesis.web

import io.mboettger.bachelorthesis.web.model.CreateCustomerRequestV1
import io.mboettger.bachelorthesis.web.model.CreateCustomerResponseV1
import io.mboettger.bachelorthesis.web.model.CustomerResponseV1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/customer")
interface CustomerController {

    @GetMapping("{id}")
    fun getById(@PathVariable id: String): CustomerResponseV1

    @GetMapping("/by-email/{email}")
    fun getByEmail(@PathVariable email: String): CustomerResponseV1

    @PostMapping
    fun create(@RequestBody request: CreateCustomerRequestV1): CreateCustomerResponseV1
}