package rodyapal.mirea.model

import io.github.crackthecodeabhi.kreds.connection.Endpoint
import io.github.crackthecodeabhi.kreds.connection.KredsClient
import io.github.crackthecodeabhi.kreds.connection.newClient

const val REDIS_HOST = "redis"
const val REDIS_PORT = 6379

suspend fun <T> redis(block: suspend (client: KredsClient) -> T): T =
	newClient(Endpoint.from("$REDIS_HOST:$REDIS_PORT")).use { block(it) }