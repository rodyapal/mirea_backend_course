package rodyapal.mirea.model

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import rodyapal.mirea.model.database.Users
import rodyapal.mirea.model.database.Valuables

object AppDb {
	const val DB_USER = "user"
	const val DB_PASSWORD = "password"
	const val DB_URL = "jdbc:mysql://localhost:3306/appDb"
	const val DB_DRIVER = "com.mysql.cj.jdbc.Driver"

	val self = Database.connect(
		url = DB_URL,
		user = DB_USER,
		password = DB_PASSWORD,
		driver = DB_DRIVER
	)

	fun setup() {
		transaction(self) {
			SchemaUtils.create(Users, Valuables)
		}
	}

	suspend fun <T> query(block: suspend () -> T): T =
		newSuspendedTransaction(Dispatchers.IO) {
			addLogger(StdOutSqlLogger)
			block()
		}
}