plugins {
  id("zeropoint.primitive.androidLibrary")
  id("zeropoint.primitive.android.compose")
  id("zeropoint.primitive.test")
  id("zeropoint.primitive.test.android")
  id("zeropoint.primitive.kotlinEnvironment")
}

android.namespace = "black.bracken.zeropoint.feature.home"

dependencies {
  implementation(project(":uishare"))
  implementation(project(":resource"))
  implementation(project(":data:kernel"))

  implementation(libs.coil)
  implementation(libs.coilCompose)
}
