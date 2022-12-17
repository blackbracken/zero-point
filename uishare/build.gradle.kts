import black.bracken.zeropoint.buildlogic.dsl.implementation
import black.bracken.zeropoint.buildlogic.dsl.libs

plugins {
  id("zeropoint.primitive.androidLibrary")
  id("zeropoint.primitive.android.compose")
  id("zeropoint.conventional.kotlinx")
}

android.namespace = "black.bracken.zeropoint.uishare"

dependencies {
  api(project(":data:kernel"))
  api(project(":resource"))
  api(project(":util"))

  implementation(libs.findLibrary("androidxComposeMaterial"))
}
