package fr.xibalba.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.core.init.InitKobweb
import com.varabyte.kobweb.core.init.InitKobwebContext
import com.varabyte.kobweb.navigation.UpdateHistoryMode
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.components.style.toModifier
import org.jetbrains.compose.web.css.vh

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        initLocale()
        key(locale) {
            Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
                content()
            }
        }
    }
}

@InitKobweb
fun add404Handler(context: InitKobwebContext) {
    context.router.setErrorHandler {
        if (it != 404) return@setErrorHandler
        context.router.navigateTo(
            "/",
            updateHistoryMode = UpdateHistoryMode.REPLACE
        )
    }
}