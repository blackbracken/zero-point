plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

dependencies {
  implementation(libs.kotlinxCoroutines)
  implementation(libs.kotlinxCoroutinesTest)

  api(libs.kotlinxSerializationJson)
  api(libs.kotlinxDatetime)
}
