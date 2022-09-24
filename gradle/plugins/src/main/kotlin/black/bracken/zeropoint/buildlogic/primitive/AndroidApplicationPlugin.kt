package black.bracken.zeropoint.buildlogic.primitive

import black.bracken.zeropoint.buildlogic.dsl.androidApplication
import black.bracken.zeropoint.buildlogic.dsl.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.application")
      }

      androidApplication {
        setupAndroid()

        buildTypes {
          release {
            isMinifyEnabled = false
            proguardFiles(
              getDefaultProguardFile("proguard-android-optimize.txt"),
              "proguard-rules.pro"
            )
          }
        }
      }
    }
  }
}
