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
    register("androidApplication") {
      id = "zeropoint.primitive.androidApplication"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.AndroidApplicationPlugin"
    }

    register("androidLibrary") {
      id = "zeropoint.primitive.androidLibrary"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.AndroidLibraryPlugin"
    }

    register("androidCompose") {
      id = "zeropoint.primitive.android.compose"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.AndroidComposePlugin"
    }

    register("androidHilt") {
      id = "zeropoint.primitive.android.hilt"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.AndroidHiltPlugin"
    }

    register("detekt") {
      id = "zeropoint.primitive.detekt"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.DetektPlugin"
    }

    register("test") {
      id = "zeropoint.primitive.test"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.TestPlugin"
    }

    register("testJvm") {
      id = "zeropoint.primitive.test.jvm"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.TestJvmPlugin"
    }
  }
}
