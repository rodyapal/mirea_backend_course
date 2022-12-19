package rodyapal.mirea.model

data class AdaptivePageParams(
	val userName: String,
	val theme: Themes,
	val lang: Langs
) {
	enum class Themes {
		DARK, LIGHT
	}

	enum class Langs {
		RU, EN
	}
}