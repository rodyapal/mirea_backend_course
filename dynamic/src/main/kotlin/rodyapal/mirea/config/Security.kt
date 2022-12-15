package rodyapal.mirea.config

import io.ktor.server.application.*

fun Application.configureSecurity() {

//	data class MySession(val count: Int = 0)
//	install(Sessions) {
//		cookie<MySession>("MY_SESSION") {
//			cookie.extensions["SameSite"] = "lax"
//		}
//	}
//
//	routing {
//		get("/session/increment") {
//			val session = call.sessions.get<MySession>() ?: MySession()
//			call.sessions.set(session.copy(count = session.count + 1))
//			call.respondText("Counter is ${session.count}. Refresh to increment.")
//		}
//	}
}
