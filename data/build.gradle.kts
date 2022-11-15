plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

dependencies {
  implementation(libs.kotlinxCoroutines)
  implementation(libs.kotlinxCoroutinesTest)

  implementation(libs.kotlinxSerializationJson)
  implementation(libs.kotlinxDatetime)

  implementation(libs.ktorClientCore)
  implementation(libs.ktorClientOkhttp)
}
