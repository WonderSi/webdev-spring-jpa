package com.example.lab3.application.service

import com.example.lab3.domain.model.User
import com.example.lab3.domain.port.UserRepositoryPort
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepositoryPort: UserRepositoryPort
) {
    fun findAll(): List<User> = userRepositoryPort.findAll()

    fun findById(id: Long): User = userRepositoryPort.findById(id)
            ?: throw NoSuchElementException("User with id=$id not found")

    fun create(user: User): Pair<User, Boolean> {
        val existing = userRepositoryPort.findByEmail(user.email)
        return if (existing != null) {
            Pair(existing, false)
        } else {
            Pair(userRepositoryPort.save(user), true)
        }
    }

    fun update(id: Long, user: User): User {
        userRepositoryPort.findById(id)
            ?: throw NoSuchElementException("User with id=$id not found")
        return userRepositoryPort.update(user.copy(id = id))
    }

    fun delete(id: Long) {
        val deleted = userRepositoryPort.deleteById(id)
        if (!deleted) throw NoSuchElementException("User with id=$id not found")
    }
}