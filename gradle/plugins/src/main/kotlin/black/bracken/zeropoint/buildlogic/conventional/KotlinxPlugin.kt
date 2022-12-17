package black.bracken.zeropoint.buildlogic.conventional

import black.bracken.zeropoint.buildlogic.dsl.implementation
import black.bracken.zeropoint.buildlogic.dsl.libs
import black.bracken.zeropoint.buildlogic.dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinxPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("org.jetbrains.kotlin.plugin.serialization")
      }

      with(project) {
        dependencies {
          implementation(libs.findLibrary("kotlinxCoroutines"))
          implementation(libs.findLibrary("kotlinxCollectionsImmutable"))
          implementation(libs.findLibrary("kotlinxSerializationJson"))
          implementation(libs.findLibrary("kotlinxDatetime"))

          testImplementation(libs.findLibrary("kotlinxCoroutines"))
          testImplementation(libs.findLibrary("kotlinxCoroutinesTest"))
          testImplementation(libs.findLibrary("kotlinxCollectionsImmutable"))
          testImplementation(libs.findLibrary("kotlinxSerializationJson"))
          testImplementation(libs.findLibrary("kotlinxDatetime"))
        }
      }
    }
  }
}
