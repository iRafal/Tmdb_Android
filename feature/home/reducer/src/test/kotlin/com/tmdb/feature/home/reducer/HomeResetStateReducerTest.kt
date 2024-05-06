package com.tmdb.feature.home.reducer

import com.tmdb.feature.home.reducer.effects.HomeFeatureEffects
import com.tmdb.store.action.HomeAction
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.AppState
import com.tmdb.store.state.HomeFeatureState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions

class HomeResetStateReducerTest {
    private val homeEffects = mock<HomeFeatureEffects>()
    private val reducer: HomeFeatureReducer
        get() = HomeFeatureReducer(homeEffects)

    @Test
    fun `reduceHomeResetState with not initial feature state test`() {
        val inputState = AppState.INITIAL.copy(
            homeState = HomeFeatureState(isFullReload = true)
        )
        val action = HomeAction.ResetState
        val expectedEffect = Effects.empty<AppEnv>()

        val (state, effect) = reducer.reduce(inputState, action)

        verifyNoInteractions(homeEffects)
        assertTrue(state.isInitial)
        assertEquals(expected = expectedEffect, actual = effect)
    }

    @Test
    fun `internal reduceHomeResetState with not initial feature state test`() {
        val inputState = HomeFeatureState(isFullReload = true)
        val expectedEffect = Effects.empty<AppEnv>()

        val (state, effect) = inputState.reduceHomeResetState()

        verifyNoInteractions(homeEffects)
        assertTrue(state.isInitial)
        assertEquals(expected = expectedEffect, actual = effect)
    }

    @Test
    fun `reduceHomeResetState with initial feature state test`() {
        val inputState = AppState.INITIAL
        val action = HomeAction.ResetState
        val expectedEffect = Effects.empty<AppEnv>()

        val (state, effect) = reducer.reduce(inputState, action)

        verifyNoInteractions(homeEffects)
        assertTrue(state.isInitial)
        assertEquals(expected = expectedEffect, actual = effect)
    }

    @Test
    fun `internal reduceHomeResetState with initial feature state test`() {
        val inputState = HomeFeatureState.INITIAL
        val expectedEffect = Effects.empty<AppEnv>()

        val (state, effect) = inputState.reduceHomeResetState()

        verifyNoInteractions(homeEffects)
        assertTrue(state.isInitial)
        assertEquals(expected = expectedEffect, actual = effect)
    }
}
