package io.mboettger.bachelorthesis.usecase.impl.useCase

import io.mboettger.bachelorthesis.usecase.boundary.InputBoundary
import kotlin.reflect.KClass

internal typealias UseCases = Map<KClass<out InputBoundary<*, *, *>>, () -> InputBoundary<*, *, *>>