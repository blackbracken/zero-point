package black.bracken.zeropoint.buildlogic.primitive

import black.bracken.zeropoint.buildlogic.dsl.android
import black.bracken.zeropoint.buildlogic.dsl.androidTestImplementation
import black.bracken.zeropoint.buildlogic.dsl.debugImplementation
import black.bracken.zeropoint.buildlogic.dsl.implementation
import black.bracken.zeropoint.buildlogic.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposePlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      android {
        buildFeatures.compose = true
        composeOptions {
          kotlinCompilerExtensionVersion =
            libs.findVersion("androidxCompose").get().toString()
        }
      }

      dependencies {
        implementation(libs.findLibrary("androidxActivityCompose"))
        implementation(libs.findLibrary("androidxComposeUi"))
        implementation(libs.findLibrary("androidxComposeUiToolingPreview"))
        implementation(libs.findLibrary("androidxComposeMaterial"))

        androidTestImplementation(libs.findLibrary("androidxComposeUiTestJunit4"))
        debugImplementation(libs.findLibrary("androidxComposeUiTooling"))
        debugImplementation(libs.findLibrary("androidxComposeUiTestManifest"))
      }
    }
  }
}
