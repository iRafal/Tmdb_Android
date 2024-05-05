package com.tmdb.ui.core.compose.event

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.tmdb.ui.core.compose.state.StateEvent
import com.tmdb.ui.core.compose.state.StateEventWithContent
import com.tmdb.ui.core.compose.state.StateEventWithContentConsumed
import com.tmdb.ui.core.compose.state.StateEventWithContentTriggered
import com.tmdb.ui.core.compose.state.consumed
import kotlin.coroutines.CoroutineContext


/**
 * A side effect that gets executed when the given [event] changes to its triggered state.
 *
 * Other than the regular [EventEffect] this one will
 * consume the event before actually executing the action.
 *
 * @param event Pass the state event to be listened to from your view-state.
 * @param onConsumed Set the passed [event] to [StateEvent.Consumed] in your view-state.
 * @param action Callback that gets called in the composition's [CoroutineContext].
 * Perform the actual action this [event] leads to.
 */
@Composable
@NonRestartableComposable
fun NavigationEventEffect(
    event: StateEvent?,
    onConsumed: () -> Unit,
    action: suspend () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    LaunchedEffect(key1 = event, key2 = onConsumed) {
        if (event is StateEvent.Triggered) {
            keyboardController?.hide()
            focusManager.clearFocus()
            onConsumed()
            action()
        }
    }
}

/**
 * A side effect that gets executed when the given [event] changes to its triggered state.
 *
 * Other than the regular [EventEffect] this one will
 * consume the event before actually executing the action.
 *
 * @param event Pass the state event of type [T] to be listened to from your view-state.
 * @param onConsumed In this callback you are advised to set the passed [event] to an instance of
 * [StateEventWithContentConsumed] in your view-state (see [consumed]).
 * @param action Callback that gets called in the composition's [CoroutineContext].
 * Perform the actual action this [event] leads to. The actual content of the [event] will be passed as an argument.
 */
@Composable
@NonRestartableComposable
fun <T> NavigationEventEffect(
    event: StateEventWithContent<T>?,
    onConsumed: () -> Unit,
    action: suspend (T) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    LaunchedEffect(key1 = event, key2 = onConsumed) {
        if (event is StateEventWithContentTriggered<T>) {
            keyboardController?.hide()
            focusManager.clearFocus()
            onConsumed()
            action(event.content)
        }
    }
}
