package com.tmdb.feature.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb.feature.home.ui.R
import com.tmdb.ui.core.compose.theme.TmdbTheme
import com.tmdb.ui.core.compose.units.Spacing

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun MovieSectionErrorPreview() {
    TmdbTheme {
        MovieSectionError(
            buttonText = stringResource(id = R.string.reload),
            onReloadSection = { }
        )
    }
}

@Composable
internal fun MovieSectionError(buttonText: String, onReloadSection: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = Spacing.dp16)
    ) {
        Button(onClick = { onReloadSection() }) {
            Text(text = buttonText)
        }
    }
}
