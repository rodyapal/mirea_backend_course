package rodyapal.mirea.routes

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import rodyapal.mirea.model.file.RedisFileStorage
import java.io.File

fun Route.pdfLoadRoute() {
	route("pdf") {
		get {

		}
		post("/upload") {
			val multipartData = call.receiveMultipart()
			var fileDescription = ""
			var fileName = ""
			multipartData.forEachPart { part ->
				when (part) {
					is PartData.FormItem -> {
						fileDescription = part.value
					}

					is PartData.FileItem -> {
						fileName = part.originalFileName as String
						val fileBytes = part.streamProvider().readBytes()
						File(fileName).apply {
							writeBytes(fileBytes)
						}.let {
							RedisFileStorage.save(it)
						}
					}

					else -> {}
				}
				part.dispose()
			}

			call.respondText("$fileDescription is uploaded to 'uploads/$fileName'")
		}
	}
}