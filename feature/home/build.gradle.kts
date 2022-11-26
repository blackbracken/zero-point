import black.bracken.zeropoint.buildlogic.dsl.implementation

plugins {
  id("zeropoint.primitive.androidLibrary")
  id("zeropoint.primitive.android.compose")
  id("zeropoint.primitive.test")
  id("zeropoint.primitive.test.android")
}

android.namespace = "black.bracken.zeropoint.feature.home"

dependencies {
  implementation(project(":uishare"))
  implementation(project(":resource"))
  implementation(project(":data:kernel"))
}
