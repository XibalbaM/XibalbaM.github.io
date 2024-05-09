package fr.xibalba.portfolio.pages

import CommonText
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.hover
import com.varabyte.kobweb.silk.components.style.toAttrs
import fr.xibalba.portfolio.layouts.PageLayout
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

val title by ComponentStyle {
    base {
        Modifier.background(Color.white).color(Color.black)
    }
    hover {
        Modifier.background(Color.black).color(Color.white)
    }
}

@Page("/index")
@Composable
fun Index() {
    PageLayout("Home") {
        Column(Modifier.fillMaxWidth().alignItems(AlignItems.Center)) {
            H1(title.toAttrs()) {
                Text(CommonText.title("Home", "Xibalba"))
            }
        }
    }
}