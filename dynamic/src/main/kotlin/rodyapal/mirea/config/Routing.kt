package rodyapal.mirea.config

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import rodyapal.mirea.routes.adaptivePageRoute
import rodyapal.mirea.routes.pdfLoadRoute
import rodyapal.mirea.routes.userApiRouting
import rodyapal.mirea.routes.valuablesApiRoute

fun Application.configureRouting() {
	routing {
		valuablesApiRoute()
		userApiRouting()
		adaptivePageRoute()
		pdfLoadRoute()
		get("/") {
			call.respondText("Hello World!")
		}
	}
}
