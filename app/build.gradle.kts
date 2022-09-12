@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application") version "7.4.0-alpha09"
    kotlin("android") version "1.6.20"
}

android {
    namespace = "black,bracken.zeropoint"
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
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:1.2.0-beta01")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0-beta01")
    implementation("androidx.compose.material:material:1.2.0-beta01")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0-beta01")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0-beta01")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0-beta01")
}