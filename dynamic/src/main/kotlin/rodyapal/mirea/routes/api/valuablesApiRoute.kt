package rodyapal.mirea.routes.api

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import rodyapal.mirea.model.database.AppDb
import rodyapal.mirea.model.database.ValuableDto
import rodyapal.mirea.model.database.ValuableEntity

fun Route.valuablesApiRoute() {
	route("/api/v1/valuables") {
		get {
			call.parameters["id"]?.toIntOrNull()?.let {
				call.respond(AppDb.query { ValuableEntity[it].idData })
			} ?: AppDb.query { ValuableEntity.all().map { it.idData } }.let {
				if (it.isNotEmpty()) {
					call.respond(it)
				} else {
					call.respondText("No users found", status = HttpStatusCode.OK)
				}
			}
			call.respondText { "data" }
		}
		post {
			val valuable = call.receive<ValuableDto>()
			AppDb.query {
				ValuableEntity.new {
					title = valuable.title
					description = valuable.description
					cost = valuable.cost
				}
			}
			call.respondText("User stored correctly", status = HttpStatusCode.Created)
		}
		delete {
			call.parameters["id"]?.toIntOrNull()?.let {
				AppDb.query { ValuableEntity[it].delete() }
			} ?: call.respondText("Invalid id param", status = HttpStatusCode.BadRequest)
		}
		patch {
			val id = call.parameters["id"]?.toIntOrNull() ?: return@patch call.respondText(
				"Invalid id param",
				status = HttpStatusCode.BadRequest
			)
			AppDb.query {
				ValuableEntity[id].let { entity ->
					call.parameters["title"]?.let { entity.title = it }
					call.parameters["description"]?.let { entity.description = it }
					call.parameters["cost"]?.toIntOrNull()?.let { entity.cost = it }
				}
			}
		}
	}
}