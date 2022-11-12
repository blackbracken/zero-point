plugins {
  id("zeropoint.primitive.androidLibrary")
  id("zeropoint.primitive.android.compose")
}

android.namespace = "black.bracken.zeropoint.featurecommon"

dependencies {
  api(project(":util"))
}
