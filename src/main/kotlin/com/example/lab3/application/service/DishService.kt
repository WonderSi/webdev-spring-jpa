package com.example.lab3.application.service

import com.example.lab3.domain.model.Dish
import com.example.lab3.domain.port.DishRepositoryPort
import org.springframework.stereotype.Service

@Service
class DishService(
    private val dishRepositoryPort: DishRepositoryPort
) {
    fun findAll(namePart: String?): List<Dish> =
        if (namePart != null) {
            dishRepositoryPort.findAllByNamePart(namePart)
        } else {
            dishRepositoryPort.findAll()
        }

    fun findById(id: Long): Dish =
        dishRepositoryPort.findById(id)
            ?: throw NoSuchElementException("Dish with id=$id not found")

    fun create(dish: Dish): Pair<Dish, Boolean> {
        val existing = dishRepositoryPort.findByName(dish.name)
        return if (existing != null) {
            Pair(existing, false)
        } else {
            Pair(dishRepositoryPort.save(dish), true)
        }
    }

    fun update(id: Long, dish: Dish): Dish {
        dishRepositoryPort.findById(id)
            ?: throw NoSuchElementException("Dish with id=$id not found")
        return dishRepositoryPort.update(dish.copy(id = id))
    }

    fun delete(id: Long) {
        val deleted = dishRepositoryPort.deleteById(id)
        if (!deleted) throw NoSuchElementException("Dish with id=$id not found")
    }
}