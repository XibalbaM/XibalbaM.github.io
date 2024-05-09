package fr.xibalba.portfolio.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.mdi.MdiDarkMode
import com.varabyte.kobweb.silk.components.icons.mdi.MdiLightMode
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.ComponentVariant
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import fr.xibalba.portfolio.locale
import fr.xibalba.portfolio.switchLocale
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Text

val HEADER_HEIGHT = 75.px

val HeaderStyle by ComponentStyle.base {
    Modifier
        .background(Color("#f5f5f5"))
        .position(Position.Sticky)
        .top(0.px).right(0.px).left(0.px)
        .width(100.percent).height(HEADER_HEIGHT)
}

@Composable
fun Header(modifier: Modifier = Modifier, variant: ComponentVariant? = null) {
    val finalModifier = HeaderStyle.toModifier(variant).then(modifier)
    var theme by ColorMode.currentState
    val ctx = rememberPageContext()
    Row(finalModifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
        Button(onClick = {
            switchLocale()
            ctx.router.navigateTo(ctx.route.path)
        }) {
            Text(locale.uppercase())
        }
        Button(onClick = {
            theme = theme.opposite
        }) {
            if (theme.isLight) {
                MdiDarkMode()
            } else {
                MdiLightMode()
            }
        }
    }
}