package black.bracken.zeropoint.buildlogic.primitive

import black.bracken.zeropoint.buildlogic.dsl.androidApplication
import black.bracken.zeropoint.buildlogic.dsl.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.application")
      }

      androidApplication {
        setupAndroid()
      }
    }
  }
}
