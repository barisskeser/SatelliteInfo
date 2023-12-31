package com.baris.feature.satellites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.baris.core.components.LoadingProgress
import com.baris.core.components.SatellitesDialog
import com.baris.core.components.SearchBar
import com.baris.domain.model.Satellite

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */

@Composable
fun SatellitesScreen(
    viewModel: SatellitesViewModel,
    onSatelliteClick: (Int, String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val event by viewModel.event.collectAsStateWithLifecycle(SatellitesViewModel.Event.Init)

    SatellitesScreenContent(
        state = state,
        event = event,
        onQueryChanged = viewModel::onQueryChanged,
        onSatelliteClick = onSatelliteClick
    )
}

@Composable
fun SatellitesScreenContent(
    state: SatellitesViewModel.State,
    event: SatellitesViewModel.Event,
    onQueryChanged: (String) -> Unit,
    onSatelliteClick: (Int, String) -> Unit
) {

    when (event) {
        SatellitesViewModel.Event.Init -> {}
        is SatellitesViewModel.Event.ShowError -> {
            SatellitesDialog(
                title = stringResource(id = R.string.warning),
                text = event.resultError.message
            )
        }
    }

    Column(
        Modifier.fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier.padding(16.dp),
            placeHolder = stringResource(id = R.string.search),
            onQueryChange = onQueryChanged
        )

        when (state) {
            is SatellitesViewModel.State.Empty -> {}
            is SatellitesViewModel.State.Loading -> {
                LoadingProgress()
            }

            is SatellitesViewModel.State.Success -> {
                SatelliteList(
                    satelliteList = state.data,
                    onSatelliteClick = onSatelliteClick
                )
            }
        }
    }
}

@Composable
fun SatelliteList(
    satelliteList: List<Satellite>,
    onSatelliteClick: (Int, String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(top = 30.dp)
    ) {
        itemsIndexed(satelliteList) { index, item ->
            SatelliteItem(
                satellite = item,
                isLast = index == satelliteList.size - 1,
                onClick = onSatelliteClick
            )
        }
    }
}

@Preview
@Composable
fun SatellitesScreenPreview() {
    val mockList = listOf(
        Satellite(
            1, true, "Satellite-1"
        ),
        Satellite(
            2, true, "Satellite-2"
        ),
        Satellite(
            3, false, "Satellite-3"
        ),
        Satellite(
            4, true, "Satellite-4"
        )
    )
    SatellitesScreenContent(
        state = SatellitesViewModel.State.Success(mockList),
        event = SatellitesViewModel.Event.Init,
        onQueryChanged = {},
        onSatelliteClick = { _, _ -> }
    )
}