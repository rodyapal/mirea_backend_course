package rodyapal.mirea.model.session

import io.github.crackthecodeabhi.kreds.connection.Endpoint
import io.github.crackthecodeabhi.kreds.connection.KredsClient
import io.github.crackthecodeabhi.kreds.connection.newClient
import io.ktor.server.sessions.*
import rodyapal.mirea.APP_HOST

private const val REDIS_PORT = 6379
class RedisSessionStorage : SessionStorage {
	override suspend fun invalidate(id: String): Unit = redis {
		it.expire(id, 1u)
	}

	override suspend fun read(id: String): String = redis {
		it.get(id) ?: throw NoSuchElementException("Session $id not found")
	}

	override suspend fun write(id: String, value: String): Unit = redis {
		it.set(id, value)
	}

	private suspend fun <T> redis(block: suspend (client: KredsClient) -> T): T =
		newClient(Endpoint.from("$APP_HOST:$REDIS_PORT")).use { block(it) }
}