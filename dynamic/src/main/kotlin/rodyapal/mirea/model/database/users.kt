package rodyapal.mirea.model.database

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable("users") {
	val name = varchar("name", 20)
	val password = varchar("password", 45)
}

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<UserEntity>(Users)
	var name by Users.name
	var password by Users.password

	val data get() = UserDto(name, password)
	val idData get() = UserIdDto(id.value, name, password)
}

@Serializable
data class UserDto(
	val name: String,
	val password: String
)

@Serializable
data class UserIdDto(
	val id: Int,
	val name: String,
	val password: String
)