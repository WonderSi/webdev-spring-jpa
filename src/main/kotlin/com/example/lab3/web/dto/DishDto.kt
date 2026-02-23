package com.example.lab3.web.dto

import com.example.lab3.domain.model.Dish
import java.math.BigDecimal

data class DishCreateRequest(
    val name: String,
    val description: String,
    val price: BigDecimal,
    val isAvailable: Boolean = true
)

data class DishUpdateRequest(
    val name: String,
    val description: String,
    val price: BigDecimal,
    val isAvailable: Boolean
)

data class DishResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val isAvailable: Boolean
)

fun Dish.toResponse() = DishResponse(
    id = id,
    name = name,
    description = description,
    price = price,
    isAvailable = isAvailable
)

fun DishCreateRequest.toDomain() = Dish(
    name = name,
    description = description,
    price = price,
    isAvailable = isAvailable
)

fun DishUpdateRequest.toDomain() = Dish(
    name = name,
    description = description,
    price = price,
    isAvailable = isAvailable
)