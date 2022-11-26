package black.bracken.zeropoint.buildlogic.primitive

import black.bracken.zeropoint.buildlogic.dsl.androidTestImplementation
import black.bracken.zeropoint.buildlogic.dsl.libs
import black.bracken.zeropoint.buildlogic.dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class TestAndroidPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      dependencies {
        testImplementation(libs.findLibrary("mockkAndroid"))
        testImplementation(libs.findLibrary("mockkAgent"))

        androidTestImplementation(libs.findLibrary("mockkAndroid"))
        androidTestImplementation(libs.findLibrary("mockkAgent"))
      }
    }
  }
}
