plugins {
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.kotlinAndroid) apply false
  alias(libs.plugins.detekt) apply false
  alias(libs.plugins.ktlintGradle) apply false
}

buildscript {
  dependencies {
    classpath(libs.hiltGradlePlugin)
    classpath(libs.detektGradlePlugin)
  }
}

allprojects {
  afterEvaluate { // for version catalog
    apply(plugin = libs.plugins.ktlintGradle.get().pluginId)

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
      debug.set(true)
    }
  }
}
