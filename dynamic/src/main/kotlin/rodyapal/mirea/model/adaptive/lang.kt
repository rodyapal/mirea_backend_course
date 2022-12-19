package rodyapal.mirea.model.adaptive

interface LangProvider {
	val username: String
	val themeChanger: String
	val langChanger: String
	val button: String
}

object EnLangProvider : LangProvider {
	override val username = "Username:"
	override val themeChanger = "Theme changer:"
	override val langChanger = "Lang changer:"
	override val button = "Submit"
}

object RuLangProvider : LangProvider {
	override val username = "Имя пользователя:"
	override val themeChanger = "Изменить тему:"
	override val langChanger = "Изменить язык:"
	override val button = "Подтвердить"
}