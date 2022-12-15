package rodyapal.mirea.config

import io.ktor.server.application.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import rodyapal.mirea.model.session.RedisSessionStorage
import rodyapal.mirea.model.session.UserSession

fun Application.configureSession() {
	install(Sessions) {
		val secretEncryptKey = hex("00112233445566778899aabbccddeeff")
		val secretSignKey = hex("6819b57a326945c1968f45236589")
		cookie<UserSession>("user_session", RedisSessionStorage()) {
			cookie.maxAgeInSeconds = 86400 // 1 day
			cookie.secure = true
			transform(
				SessionTransportTransformerEncrypt(
					secretEncryptKey, secretSignKey
				)
			)
		}
	}
}