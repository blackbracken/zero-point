plugins {
  kotlin("jvm")

  id("zeropoint.conventional.kotlinx")
}

dependencies {
  implementation(libs.kotlinxCoroutines)
  implementation(libs.kotlinxCoroutinesTest)
  implementation(libs.junit)
  implementation(libs.kotestAssertion)
}
