package black.bracken.zeropoint.util.test

import io.kotest.matchers.collections.shouldContainExactly

class UiStateScenario<out S : Any, L : S> private constructor(
  val history: List<S>,
) {

  @Suppress("UNCHECKED_CAST")
  fun <N : L> then(makeNewState: L.() -> N): UiStateScenario<S, N> =
    UiStateScenario(history + makeNewState(history.last() as L))

  companion object {
    fun <S : Any> beginsWith(initialState: S): UiStateScenario<S, S> =
      UiStateScenario(listOf(initialState))
  }
}

fun <S : Any> List<S>.shouldFollowUiStateScenario(
  scenario: UiStateScenario<S, *>,
) = this.shouldContainExactly(scenario.history)
