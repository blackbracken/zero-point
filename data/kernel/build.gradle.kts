plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
  id("zeropoint.primitive.test")
}

dependencies {
  implementation(libs.kotlinxCoroutines)
  implementation(libs.kotlinxCoroutinesTest)

  api(libs.kotlinxSerializationJson)
  api(libs.kotlinxDatetime)
}
