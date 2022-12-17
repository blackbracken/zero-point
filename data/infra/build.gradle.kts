plugins {
  id("zeropoint.primitive.androidLibrary")
  id("zeropoint.conventional.kotlinx")
}

android.namespace = "black.bracken.zeropoint.data.infra"

dependencies {
  implementation(project(":data:kernel"))

  implementation(libs.androidxDataStore)

  implementation(libs.ktorClientCore)
  implementation(libs.ktorClientOkhttp)
}
