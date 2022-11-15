@file:Suppress("UnstableApiUsage")

import black.bracken.zeropoint.buildlogic.dsl.implementation
import black.bracken.zeropoint.buildlogic.dsl.libs

plugins {
  id("zeropoint.primitive.androidApplication")
  id("zeropoint.primitive.android.hilt")
  id("zeropoint.primitive.android.compose")
  id("zeropoint.primitive.test")
  id("zeropoint.primitive.test.jvm")
  id("zeropoint.primitive.detekt")
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
  implementation(project(":data"))
  implementation(project(":featurecommon"))
  implementation(project(":feature:setup"))

  implementation(libs.findLibrary("androidxComposeMaterial"))
}
