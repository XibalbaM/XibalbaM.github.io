package fr.xibalba.portfolio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.AppGlobals
import de.comahe.i18n4k.Locale
import de.comahe.i18n4k.config.I18n4kConfigDefault
import de.comahe.i18n4k.i18n4k
import de.comahe.i18n4k.language
import web.navigator.navigator
import web.storage.localStorage

private val i18n4kConfig = I18n4kConfigDefault()

var locale by mutableStateOf(i18n4kConfig.locale.language)

fun initLocale() {
    val language =
        localStorage.getItem("language")
        ?: navigator.languages.map { it.substringBefore('-') }.firstOrNull {
            it in (AppGlobals["languages"]?.split(',') ?: throw IllegalStateException("Languages not set"))
        }
        ?: "en"
    i18n4k = i18n4kConfig
    i18n4kConfig.locale = Locale(language)
}

fun setLocale(language: String) {
    require(language in AppGlobals["languages"]!!.split(','))
    localStorage.setItem("language", language)
    i18n4kConfig.locale = Locale(language)
    locale = language
}

fun switchLocale() {
    val language = locale
    val languages = AppGlobals["languages"]!!.split(',')
    val index = languages.indexOf(language)
    setLocale(languages[(index + 1) % languages.size])
}