plugins {
//    id("com.android.application") version "7.4.0-alpha09"
//    id("com.android.library") version "7.4.0-alpha09"
//    id("org.jetbrains.kotlin.android") version "1.6.21"
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.kotlinAndroid) apply false

  id(libs.plugins.ktlint.get().pluginId) apply false
}

allprojects {
  afterEvaluate { // for version catalog
    apply(plugin = libs.plugins.ktlint.get().pluginId)

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
      debug.set(true)
    }
  }
}
