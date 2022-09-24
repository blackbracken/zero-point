package black.bracken.zeropoint.buildlogic.primitive

import black.bracken.zeropoint.buildlogic.dsl.androidLibrary
import black.bracken.zeropoint.buildlogic.dsl.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.library")
      }

      androidLibrary {
        setupAndroid()
      }
    }
  }
}
