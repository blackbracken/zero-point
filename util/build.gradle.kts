plugins {
  kotlin("jvm")
  id("zeropoint.primitive.kotlinEnvironment")
}

dependencies {
  implementation(libs.kotlinxCoroutines)
  implementation(libs.kotlinxCoroutinesTest)
  implementation(libs.junit)
  implementation(libs.kotestAssertion)
}
