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
    // primitive
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

    register("kotlinEnvironment") {
      id = "zeropoint.primitive.kotlinEnvironment"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.KotlinEnvironmentPlugin"
    }


    register("test") {
      id = "zeropoint.primitive.test"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.TestPlugin"
    }

    register("testAndroid") {
      id = "zeropoint.primitive.test.android"
      implementationClass = "black.bracken.zeropoint.buildlogic.primitive.TestAndroidPlugin"
    }
  }
}
