package rodyapal.mirea.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.thymeleaf.*
import rodyapal.mirea.model.AdaptivePageParams
import rodyapal.mirea.model.session.UserSession

fun Route.adaptivePageRoute() {
	route("/adaptive") {
		get {
			val session = call.sessions.get<UserSession>()
			call.respond(
				ThymeleafContent("adaptive", mapOf(
					"params" to AdaptivePageParams(
						userName = session?.name ?: "",
						isRu = false,
						isDark = false
					)
				))
			)
		}
	}
}