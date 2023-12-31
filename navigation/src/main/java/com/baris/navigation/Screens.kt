package com.baris.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.baris.common.Params
import com.baris.feature.satellitedetail.SatelliteDetailScreen
import com.baris.feature.satellites.SatellitesScreen

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */

fun NavGraphBuilder.satellitesScreen(onSatelliteClick: (Int, String) -> Unit) {
    composable(
        route = Routes.SATELLITES,
        content = {
            SatellitesScreen(
                viewModel = hiltViewModel(),
                onSatelliteClick = onSatelliteClick
            )
        }
    )
}

fun NavGraphBuilder.satellitesDetailScreen() {
    composable(
        route = Routes.SATELLITE_DETAIL + "/{${Params.SATELLITE_ID}}/{${Params.SATELLITE_NAME}}",
        arguments = listOf(
            navArgument(Params.SATELLITE_ID) { type = NavType.IntType },
            navArgument(Params.SATELLITE_NAME) { type = NavType.StringType }
        ),
        content = {
            SatelliteDetailScreen(
                viewModel = hiltViewModel()
            )
        }
    )
}