package rodyapal.mirea

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.tomcat.*
import rodyapal.mirea.config.*
import rodyapal.mirea.model.AppDb

const val APP_PORT = 8001
const val APP_HOST = "0.0.0.0"

fun main() {
	AppDb.self
	embeddedServer(Tomcat, port = APP_PORT, host = APP_HOST, module = Application::module)
		.start(wait = true)
}

fun Application.module() {
	AppDb.setup()
	configureSession()
	configureSecurity()
	configureTemplating()
	configureSerialization()
	configureRouting()
	configureCORS()
}
