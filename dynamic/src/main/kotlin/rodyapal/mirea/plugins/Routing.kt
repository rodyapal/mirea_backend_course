package rodyapal.mirea.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import rodyapal.mirea.routes.userApiRouting
import rodyapal.mirea.routes.valuablesApiRoute

fun Application.configureRouting() {
	routing {
		valuablesApiRoute()
		userApiRouting()
		get("/") {
			call.respondText("Hello World!")
		}
	}
}
