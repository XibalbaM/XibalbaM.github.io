import com.google.devtools.ksp.gradle.KspTaskJS
import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import de.comahe.i18n4k.gradle.plugin.i18n4k
import kotlinx.html.*
import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile

val languages = listOf("en", "fr")

plugins {
    alias(libs.plugins.kotlin.multiplatform)

    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
    alias(libs.plugins.comahe.i18n)
}

group = "fr.xibalba"
version = "1.0-SNAPSHOT"
//TODO: Make mail signature
repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
}

kobweb {
    app {
        export {
            includeSourceMap = false
        }

        index {
            val url = "https://xibalbam.github.io"
            val author = "Maël Porret"

            val description = """
				TODO
			""".trimIndent()

            val image = "$url/images/avatar.webp"

            globals["author"] = author
            globals["description"] = description
            globals["url"] = url
            globals["languages"] = languages.joinToString(",")

            this.description = description

//            head.apply {
//                add {
//                    meta(charset = "utf-8")
//                    meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
//                    meta(name = "Author", content = author)
//
//                    meta(property = "og:description", content = description)
//                    meta(property = "og:image", content = image)
//                    meta(property = "og:type", content = "website")
//                    meta(property = "og:url", content = url)
//
//                    meta(property = "twitter:card", content = "summary")
//                    meta(property = "twitter:description", content = description)
//                    meta(property = "twitter:image", content = image)
//                }
//            }
        }
    }
}

fun HEAD.meta(property: String, content: String) {
    meta {
        attributes["property"] = property
        this.content = content
    }
}

operator fun <K : Any, V : Any> MapProperty<K, V>.set(key: K, value: V) {
    put(key, value)
}

kotlin {
    configAsKobwebApplication("portfolio")

    js(IR) {
        browser {
            commonWebpackConfig {
                val isDev = project.findProperty("kobwebEnv") == "DEV"
                sourceMaps = isDev
                devServer?.open = false
            }
        }

        binaries.executable()
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                implementation(libs.kobwebx.markdown)
                implementation(libs.silk.icons.mdi)
                implementation(libs.silk.icons.fa)
                implementation(libs.kotlinx.wrappers.browser)
                implementation(libs.comahe.i18n)

                implementation(npm("marked", project.extra["npm.marked.version"].toString()))
            }
        }
    }
}

i18n4k {
    sourceCodeLocales = languages
    inputDirectory = "src/jsMain/i18n"
}

tasks.withType<KotlinJsCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += listOf(
        "-Xklib-enable-signature-clash-checks=false",
    )
}

tasks.withType<KspTaskJS>().configureEach {
    dependsOn("generateI18n4kFiles")
}