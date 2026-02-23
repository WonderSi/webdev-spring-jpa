package com.example.lab3.infrastructure.jpa.entity

import com.example.lab3.domain.model.Dish
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "dishes")
class DishEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val name: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false, precision = 10, scale = 2)
    val price: BigDecimal,

    @Column(nullable = false)
    val isAvailable: Boolean = true
) {
    constructor() : this(0, "", "", BigDecimal.ZERO, true)

    fun toDomain() = Dish(
        id = id,
        name = name,
        description = description,
        price = price,
        isAvailable = isAvailable
    )

    companion object {
        fun fromDomain(dish: Dish) = DishEntity(
            id = dish.id,
            name = dish.name,
            description = dish.description,
            price = dish.price,
            isAvailable = dish.isAvailable
        )
    }
}