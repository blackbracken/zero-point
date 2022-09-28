package black.bracken.zeropoint.buildlogic.primitive

import black.bracken.zeropoint.buildlogic.dsl.implementation
import black.bracken.zeropoint.buildlogic.dsl.kapt
import black.bracken.zeropoint.buildlogic.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class AndroidHiltPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("kotlin-kapt")
        apply("dagger.hilt.android.plugin")
      }

      dependencies {
        implementation(libs.findLibrary("hiltAndroid"))
        kapt(libs.findLibrary("hiltAndroidCompiler"))
      }

      extensions.configure<KaptExtension> {
        correctErrorTypes = true
      }
    }
  }
}
