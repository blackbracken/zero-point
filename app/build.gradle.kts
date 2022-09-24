@file:Suppress("UnstableApiUsage")

plugins {
  kotlin("android")
  id("zeropoint.primitive.android")
  id("zeropoint.primitive.androidCompose")
  id("zeropoint.primitive.test")
  id("zeropoint.primitive.testJvm")
}

android.namespace = "black.bracken.zeropoint.app"

dependencies {
  implementation(project(":feature:choosesource"))
}

/*
android {
  namespace = "black.bracken.zeropoint"
  compileSdk = 33

  defaultConfig {
    applicationId = "black.bracken.zeropoint"
    minSdk = 28
    targetSdk = 33

    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.2.0-beta01"
  }

  packagingOptions {
    jniLibs.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
  }
}

dependencies {
  implementation(libs.androidxCoreKtx)
  implementation(libs.androidxLifecycleRuntimeKtx)
  implementation(libs.androidxActivityCompose)
  implementation(libs.androidxComposeUi)
  implementation(libs.androidxComposeUiToolingPreview)
  implementation(libs.androidxComposeMaterial)

  testImplementation(libs.junit)

  androidTestImplementation(libs.androidxTestExtJunit)
  androidTestImplementation(libs.androidxTestEspressoCore)
  androidTestImplementation(libs.androidxComposeUiTestJunit4)
  debugImplementation(libs.androidxComposeUiTooling)
  debugImplementation(libs.androidxComposeUiTestManifest)
}
*/
