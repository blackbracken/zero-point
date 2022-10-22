@file:Suppress("UnstableApiUsage")

plugins {
  id("zeropoint.primitive.androidApplication")
  id("zeropoint.primitive.android.hilt")
  id("zeropoint.primitive.android.compose")
  id("zeropoint.primitive.test")
  id("zeropoint.primitive.test.jvm")
  id("zeropoint.primitive.detekt")
}

android.namespace = "black.bracken.zeropoint.app"

dependencies {
  implementation(project(":feature:choosesource"))
}
