package com.example.lab3.infrastructure.jpa.adapter

import com.example.lab3.domain.model.Dish
import com.example.lab3.domain.port.DishRepositoryPort
import com.example.lab3.infrastructure.jpa.entity.DishEntity
import com.example.lab3.infrastructure.jpa.repository.DishJpaRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("db")
class DishJpaAdapter(
    private val dishJpaRepository: DishJpaRepository
) : DishRepositoryPort {

    override fun findAll(): List<Dish> =
        dishJpaRepository.findAll().map { it.toDomain() }

    override fun findAllByNamePart(namePart: String): List<Dish> =
        dishJpaRepository.findByNameContaining(namePart).map { it.toDomain() }

    override fun findById(id: Long): Dish? =
        dishJpaRepository.findById(id).orElse(null)?.toDomain()

    override fun findByName(name: String): Dish? =
        dishJpaRepository.findByName(name)?.toDomain()

    override fun save(dish: Dish): Dish =
        dishJpaRepository.save(DishEntity.fromDomain(dish)).toDomain()

    override fun update(dish: Dish): Dish =
        dishJpaRepository.save(DishEntity.fromDomain(dish)).toDomain()

    override fun deleteById(id: Long): Boolean {
        return if (dishJpaRepository.existsById(id)) {
            dishJpaRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}