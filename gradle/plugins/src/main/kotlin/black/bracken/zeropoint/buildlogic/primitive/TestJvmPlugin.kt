package black.bracken.zeropoint.buildlogic.primitive

import black.bracken.zeropoint.buildlogic.dsl.unsafeTestImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class TestJvmPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      dependencies {
        unsafeTestImplementation(kotlin("test"))
      }
    }
  }
}
