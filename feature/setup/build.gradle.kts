import black.bracken.zeropoint.buildlogic.dsl.implementation
import black.bracken.zeropoint.buildlogic.dsl.libs

plugins {
  id("zeropoint.primitive.androidLibrary")
  id("zeropoint.primitive.android.compose")
  id("zeropoint.primitive.kotlinEnvironment")
  id("zeropoint.primitive.test")
  id("zeropoint.primitive.test.android")
}

android.namespace = "black.bracken.zeropoint.feature.setup"

dependencies {
  implementation(project(":uishare"))
  implementation(project(":resource"))
  implementation(project(":data:kernel"))

  implementation(libs.findLibrary("androidxComposeMaterial"))
}
