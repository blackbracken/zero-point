plugins {
  kotlin("jvm")
  id("zeropoint.primitive.test")
  id("zeropoint.primitive.kotlinEnvironment")
}

dependencies {
  implementation(project(":util"))
}