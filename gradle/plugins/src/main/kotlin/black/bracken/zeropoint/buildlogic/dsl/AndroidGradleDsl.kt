/*
 * Copyright 2022 DroidKaigi committee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("UnstableApiUsage")

package black.bracken.zeropoint.buildlogic.dsl

// cf. https://github.com/DroidKaigi/conference-app-2022/blob/main/gradle/plugins/src/main/kotlin/io/github/droidkaigi/confsched2022/primitive/AndroidGradleDsl.kt

import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) {
  extensions.configure(action)
}

fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
  extensions.configure(action)
}

fun Project.android(action: TestedExtension.() -> Unit) {
  extensions.configure(action)
}

fun Project.setupAndroid() {
  android {
    if (namespace != null) {
      this.namespace = namespace
    }
    compileSdkVersion(33)

    defaultConfig {
//      applicationId = "black.bracken.zeropoint"
      minSdk = 28
      targetSdk = 33

      versionCode = 1
      versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

      vectorDrawables {
        useSupportLibrary = true
      }
    }

    compileOptions {
      sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
      targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
    }

    testOptions {
      unitTests {
        isIncludeAndroidResources = true
      }
    }
  }

  dependencies {
    implementation(libs.findLibrary("androidxCoreKtx"))

    testImplementation(libs.findLibrary("junit"))

    androidTestImplementation(libs.findLibrary("androidxTestExtJunit"))
    androidTestImplementation(libs.findLibrary("androidxTestEspressoCore"))
  }
}
