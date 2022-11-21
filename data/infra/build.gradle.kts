plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

dependencies {
  implementation(project(":data:kernel"))

  implementation(libs.kotlinxCoroutines)
  implementation(libs.kotlinxCoroutinesTest)

  implementation(libs.kotlinxSerializationJson)
  implementation(libs.kotlinxDatetime)

  implementation(libs.ktorClientCore)
  implementation(libs.ktorClientOkhttp)
}
