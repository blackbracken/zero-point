package black.bracken.zeropoint.util.test

import io.kotest.matchers.collections.shouldContainExactly

class UiStateScenario<S : Any>(
  initialState: S,
) {

  private val mutableList = mutableListOf(initialState)

  fun then(makeNewState: (S) -> S): UiStateScenario<S> {
    val newState = makeNewState(mutableList.last())
    mutableList.add(newState)

    return this
  }

  fun then(newState: S): UiStateScenario<S> = then { newState }

  fun toList(): List<S> = mutableList

}

fun <S : Any> List<S>.shouldFollowUiStateScenario(
  scenario: UiStateScenario<S>,
) = this.shouldContainExactly(scenario.toList())

