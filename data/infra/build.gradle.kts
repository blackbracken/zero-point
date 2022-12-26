plugins {
  id("zeropoint.primitive.androidLibrary")
  id("zeropoint.primitive.kotlinEnvironment")
}

android.namespace = "black.bracken.zeropoint.data.infra"

dependencies {
  implementation(project(":data:kernel"))
  implementation(project(":util"))

  implementation(libs.androidxDataStore)

  implementation(libs.ktorClientCore)
  implementation(libs.ktorClientOkhttp)
}
