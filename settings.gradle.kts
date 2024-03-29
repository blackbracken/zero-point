@file:Suppress("UnstableApiUsage")

pluginManagement {
  includeBuild("gradle/plugins")
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
  plugins {
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "ZeroPoint"

include(
  ":app",
  ":data:infra",
  ":data:kernel",
  ":gradle:plugins",
  ":uishare",
  ":feature:setup",
  ":feature:home",
  ":resource",
  ":util",
)
