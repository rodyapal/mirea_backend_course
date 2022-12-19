package rodyapal.mirea.view

import rodyapal.mirea.model.adaptive.LangProvider
import kotlinx.html.*

fun HTML.adaptivePage(
	langProvider: LangProvider,
	pageStyle: String,
	name: String,
) {
	style = pageStyle
	body {
		form(
			action = "/adaptive",
			method = FormMethod.post
		) {
			p {
				+langProvider.username
				textInput(name = "username") {
//					value = name
				}
			}
			p {
				+langProvider.themeChanger
				div {
					radioInput(name = "theme") {
						+"Change?"
					}
				}
			}
			p {
				+langProvider.langChanger
				div {
					radioInput(name = "lang") {
						+"Change?"
					}
				}
			}
			p {
				submitInput {
					+langProvider.button
					value = "Submit"
				}
			}
		}
	}
}