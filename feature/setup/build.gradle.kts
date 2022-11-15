import black.bracken.zeropoint.buildlogic.dsl.implementation
import black.bracken.zeropoint.buildlogic.dsl.libs

plugins {
  id("zeropoint.primitive.androidLibrary")
  id("zeropoint.primitive.android.compose")
}

android.namespace = "black.bracken.zeropoint.feature.setup"

dependencies {
  implementation(project(":featurecommon"))
  implementation(project(":resource"))

  implementation(libs.findLibrary("androidxComposeMaterial"))
}
