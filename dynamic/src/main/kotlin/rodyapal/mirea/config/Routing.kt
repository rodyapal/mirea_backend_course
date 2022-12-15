package rodyapal.mirea.config

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import rodyapal.mirea.routes.adaptivePageRoute
import rodyapal.mirea.routes.userApiRouting
import rodyapal.mirea.routes.valuablesApiRoute

fun Application.configureRouting() {
	routing {
		valuablesApiRoute()
		userApiRouting()
		adaptivePageRoute()
		get("/") {
			call.respondText("Hello World!")
		}
	}
}
