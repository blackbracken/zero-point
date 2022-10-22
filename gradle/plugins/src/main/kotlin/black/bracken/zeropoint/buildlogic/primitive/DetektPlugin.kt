package black.bracken.zeropoint.buildlogic.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

class DetektPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("io.gitlab.arturbosch.detekt")
      }
    }
  }
}
