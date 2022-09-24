/*
 * Copyright 2022 DroidKaigi committee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package black.bracken.zeropoint.buildlogic.dsl

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType
import java.util.Optional

// cf. https://github.com/DroidKaigi/conference-app-2022/blob/69fe6fe8269538c4fa92c32241110f94b1ff6600/gradle/plugins/src/main/kotlin/io/github/droidkaigi/confsched2022/primitive/ProjectGradleDsl.kt
// cf. https://melix.github.io/blog/2021/03/version-catalogs-faq.html#_but_how_can_i_use_the_catalog_in_plugins_defined_in_buildsrc

val Project.libs get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun DependencyHandlerScope.implementation(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("implementation", artifact.get())
}

fun DependencyHandlerScope.debugImplementation(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("debugImplementation", artifact.get())
}

fun DependencyHandlerScope.androidTestImplementation(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("androidTestImplementation", artifact.get())
}

fun DependencyHandlerScope.testImplementation(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("testImplementation", artifact.get())
}

private fun DependencyHandlerScope.api(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("api", artifact.get())
}
