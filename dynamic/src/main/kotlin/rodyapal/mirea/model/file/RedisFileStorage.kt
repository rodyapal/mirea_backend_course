package rodyapal.mirea.model.file

import rodyapal.mirea.model.redis
import java.io.File
import java.util.Vector

object RedisFileStorage {
	private val ids: Vector<String> = Vector<String>()
	val fileNames: List<String> get() = ids.toList()
	suspend fun save(file: File) = redis {
		ids.add(file.name)
		it.set(file.name, file.toString())
	}

	suspend fun delete(name: String) = redis {
		it.del(name)
	}

	suspend fun read(name: String) = redis {
		it.get(name)
	}
}