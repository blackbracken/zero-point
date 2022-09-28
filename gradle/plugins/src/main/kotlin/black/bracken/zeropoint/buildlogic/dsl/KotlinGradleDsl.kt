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

import com.android.build.gradle.TestedExtension
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.util.Optional

fun DependencyHandlerScope.kapt(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("kapt", artifact.get())
}

fun DependencyHandlerScope.kaptTest(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("kaptTest", artifact.get())
}

fun TestedExtension.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
  (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

fun DependencyHandlerScope.ksp(
  artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
  add("ksp", artifact.get())
}
