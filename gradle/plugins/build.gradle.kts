plugins {
  `kotlin-dsl`
}

group = "black.bracken.zeropoint.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
  implementation(libs.androidGradlePlugin)
  implementation(libs.kotlinGradlePlugin)
}

gradlePlugin {
  plugins {
    register("android") {
      id = "zeropoint.primitive.android"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.AndroidPlugin"
    }

    register("androidCompose") {
      id = "zeropoint.primitive.androidCompose"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.AndroidComposePlugin"
    }

    register("test") {
      id = "zeropoint.primitive.test"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.TestPlugin"
    }

    register("testJvm") {
      id = "zeropoint.primitive.testJvm"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.TestJvmPlugin"
    }
  }
}
