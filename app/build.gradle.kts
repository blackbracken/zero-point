@file:Suppress("UnstableApiUsage")

import black.bracken.zeropoint.buildlogic.dsl.implementation
import black.bracken.zeropoint.buildlogic.dsl.libs

plugins {
  id("zeropoint.primitive.androidApplication")
  id("zeropoint.primitive.android.hilt")
  id("zeropoint.primitive.android.compose")
  id("zeropoint.primitive.test")
}
android {
  buildTypes {
    getByName("release") {
      signingConfig = signingConfigs.getByName("debug")
    }
  }
}

android.namespace = "black.bracken.zeropoint.app"

dependencies {
  implementation(project(":data:kernel"))
  implementation(project(":data:infra"))
  implementation(project(":uishare"))
  implementation(project(":feature:setup"))
  implementation(project(":feature:home"))

  implementation(libs.findLibrary("androidxCoreSplashscreen"))
}
