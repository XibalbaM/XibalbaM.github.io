package fr.xibalba.portfolio

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp

@App
@Composable
fun Main(content: @Composable () -> Unit) {
    SilkApp {
        content()
    }
}