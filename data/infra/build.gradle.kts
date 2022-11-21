plugins {
  id("zeropoint.primitive.androidLibrary")
  kotlin("plugin.serialization")
}

android.namespace = "black.bracken.zeropoint.data.infra"

dependencies {
  implementation(project(":data:kernel"))

  implementation(libs.kotlinxCoroutines)
  implementation(libs.kotlinxCoroutinesTest)
  implementation(libs.kotlinxSerializationJson)
  implementation(libs.kotlinxDatetime)

  implementation(libs.androidxDataStore)

  implementation(libs.ktorClientCore)
  implementation(libs.ktorClientOkhttp)
}
