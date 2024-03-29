package black.bracken.zeropoint.buildlogic.primitive

import black.bracken.zeropoint.buildlogic.dsl.libs
import black.bracken.zeropoint.buildlogic.dsl.testImplementation
import black.bracken.zeropoint.buildlogic.dsl.unsafeTestImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class TestPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      dependencies {
        unsafeTestImplementation(kotlin("test-common"))
        unsafeTestImplementation(kotlin("test-annotations-common"))

        unsafeTestImplementation(kotlin("test")) // runs on junit

        testImplementation(libs.findLibrary("kotlinxCoroutinesTest"))

        testImplementation(libs.findLibrary("kotestAssertion"))
        testImplementation(libs.findLibrary("mockk"))
      }
    }
  }
}
