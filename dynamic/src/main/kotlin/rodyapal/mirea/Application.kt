package rodyapal.mirea

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import rodyapal.mirea.model.AppDb
import rodyapal.mirea.plugins.*

fun main() {
	AppDb.self
	embeddedServer(Tomcat, port = 8001, host = "0.0.0.0", module = Application::module)
		.start(wait = true)
}

fun Application.module() {
	AppDb.setup()
	configureSecurity()
	configureHTTP()
	configureTemplating()
	configureSerialization()
	configureRouting()
}
