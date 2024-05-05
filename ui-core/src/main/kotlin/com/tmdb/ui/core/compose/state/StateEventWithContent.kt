package com.tmdb.ui.core.compose.state

import androidx.compose.runtime.Immutable


/**
 *  This [StateEventWithContent] can have exactly 2 states like the [StateEvent],
 *  but the triggered state holds a value of type [T].
 */
@Immutable
sealed interface StateEventWithContent<T> {
    val isConsumed: Boolean
        get() = this is StateEventWithContentConsumed

    val isTriggered: Boolean
        get() = this is StateEventWithContentTriggered
}

/**
 * The event in its triggered state holding a value of [T]. See [triggered] to create an instance of this.
 * @param content A value that is needed on the event consumer side.
 */
@Immutable
class StateEventWithContentTriggered<T>(val content: T) : StateEventWithContent<T> {
    override fun toString(): String = "triggered($content)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StateEventWithContentTriggered<*>

        return content == other.content
    }

    override fun hashCode(): Int = content?.hashCode() ?: 0
}

/**
 * The event in its consumed state not holding any value. See [consumed] to create an instance of this.
 */
@Immutable
class StateEventWithContentConsumed<T> : StateEventWithContent<T> {
    override fun toString(): String = "consumed"
}

/**
 * A shorter and more readable way to create an [StateEventWithContent] in its triggered state holding a value of [T].
 * @param content A value that is needed on the event consumer side.
 */
fun <T> triggered(content: T) = StateEventWithContentTriggered(content)

/**
 * A shorter and more readable way to create an [StateEventWithContent] in its consumed state.
 */
fun <T> consumed() = StateEventWithContentConsumed<T>()
