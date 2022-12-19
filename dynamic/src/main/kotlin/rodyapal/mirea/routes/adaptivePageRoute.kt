package rodyapal.mirea.routes

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import rodyapal.mirea.model.adaptive.EnLangProvider
import rodyapal.mirea.model.adaptive.RuLangProvider
import rodyapal.mirea.model.adaptive.STYLE_DARK
import rodyapal.mirea.model.adaptive.STYLE_LIGHT
import rodyapal.mirea.model.session.AdaptiveSession
import rodyapal.mirea.view.adaptivePage

fun Route.adaptivePageRoute() {
	route("/adaptive") {
		get {
			val session = call.sessions.get<AdaptiveSession>()
				?: AdaptiveSession().also { call.sessions.set(it) }
			call.respondHtml {
				adaptivePage(
					name = session.name ?: "",
					langProvider = if (session.isRu) RuLangProvider else EnLangProvider,
					pageStyle = if (session.isDark) STYLE_DARK else STYLE_LIGHT
				)
			}
		}
		post {
			val current = call.sessions.get<AdaptiveSession>() ?: return@post call.respondText { "No session" }
			val formParams = call.receiveParameters()
			with(call) {
				sessions.set(
					current.copy(
						name = parameters["username"],
						isRu = parameters["lang"]?.let { !current.isRu } ?: current.isRu,
						isDark = parameters["theme"]?.let { !current.isDark } ?: current.isDark,
					)
				)
			}
			call.respondHtml {
				adaptivePage(
					name = current.name ?: "",
					langProvider = if (current.isRu) RuLangProvider else EnLangProvider,
					pageStyle = if (current.isDark) STYLE_DARK else STYLE_LIGHT
				)
			}
		}
		get("log") {
			call.respondText(
				call.sessions.get<AdaptiveSession>()?.toString() ?: "Null session"
			)
		}
	}
}