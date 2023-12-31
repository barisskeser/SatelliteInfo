package com.baris.feature.satellitedetail

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.baris.core.components.LoadingProgress
import com.baris.core.components.SatellitesDialog

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */

@Composable
fun SatelliteDetailScreen(
    viewModel: SatelliteDetailViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val event by viewModel.event.collectAsStateWithLifecycle(initialValue = SatelliteDetailViewModel.Event.Init)

    when (event) {
        SatelliteDetailViewModel.Event.Init -> {}
        is SatelliteDetailViewModel.Event.ShowError -> {
            SatellitesDialog(title = "Uyarı!", text = (event as SatelliteDetailViewModel.Event.ShowError).resultError.message)
        }
    }

    when (state) {
        SatelliteDetailViewModel.State.Empty -> {}
        SatelliteDetailViewModel.State.Loading -> {
            LoadingProgress()
        }

        is SatelliteDetailViewModel.State.Success -> {
            SatelliteDetail(satellite = (state as SatelliteDetailViewModel.State.Success).data)
        }
    }
}

@Composable
fun SatelliteDetail(satellite: SatelliteDetailUiModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = satellite.name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = satellite.firstFlight,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Text(
                text = "Height/Mass:",
                fontWeight = FontWeight.Bold
            )
            Text(text = "${satellite.height}/${satellite.mass}")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = "Cost:",
                fontWeight = FontWeight.Bold
            )
            Text(text = satellite.costPerLaunch.toString())
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = "Last Position:",
                fontWeight = FontWeight.Bold
            )
            Text(text = "(${satellite.posX}, ${satellite.posY})")
        }
    }
}