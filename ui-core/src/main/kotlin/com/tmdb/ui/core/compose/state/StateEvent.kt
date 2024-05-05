package com.tmdb.ui.core.compose.state

import androidx.compose.runtime.Immutable
import com.tmdb.ui.core.compose.state.StateEvent.Consumed
import com.tmdb.ui.core.compose.state.StateEvent.Triggered

/**
 *  This [StateEvent] can only have two primitive states.
 */
@Immutable
sealed interface StateEvent {
    val isConsumed: Boolean
        get() = this is Consumed

    val isTriggered: Boolean
        get() = this is Triggered

    /**
     *  The event is currently in its triggered state
     */
    @Immutable
    object Triggered : StateEvent {
        override fun toString(): String = "triggered"
    }

    /**
     *  The event is currently in its consumed state
     */
    @Immutable
    object Consumed : StateEvent {
        override fun toString(): String = "consumed"
    }
}

/**
 *  Shorter and more readable version of [StateEvent.Triggered]
 */
val triggered = Triggered

/**
 *  Shorter and more readable version of [StateEvent.Consumed]
 */
val consumed = Consumed
