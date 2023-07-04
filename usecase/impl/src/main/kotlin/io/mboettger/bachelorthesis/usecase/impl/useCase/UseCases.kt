package io.mboettger.bachelorthesis.usecase.impl.useCase

import io.mboettger.bachelorthesis.usecase.boundary.InputBoundary
import kotlin.reflect.KClass

internal typealias UseCases = Map<KClass<out InputBoundary<*, *, *>>, () -> InputBoundary<*, *, *>>

/*
TODO:
compiler contracts
extension properties
context receivers
break/continue labels
getter setter as property (incl synthetic properties)
Import aliasing
destructing
operator overloading

Done:
nothing type
null safety
infix functions
elvis operator
smart casts
extension functions
value classes
if/when with return value
no checked exceptions
implicit getter and setter (val var)
Sealed classes and interfaces
optionally no primary constructor (inline + val declaration in param list)
"template strings" (use variables and operations in strings)
delegation
named arguments
type aliasing
 */