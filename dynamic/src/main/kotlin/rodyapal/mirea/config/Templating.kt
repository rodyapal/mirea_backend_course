package rodyapal.mirea.config

import io.ktor.server.thymeleaf.Thymeleaf
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import io.ktor.server.application.*

fun Application.configureTemplating() {
	install(Thymeleaf) {
		setTemplateResolver(ClassLoaderTemplateResolver().apply {
			prefix = "templates/thymeleaf/"
			suffix = ".html"
			characterEncoding = "utf-8"
		})
	}
}
