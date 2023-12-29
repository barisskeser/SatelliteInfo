package com.baris.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */

@Composable
fun NavHost() {
    val navController = rememberNavController()

    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = Routes.SATELLITES
    ) {
        satellitesScreen(
            onSatelliteClick = { satelliteId ->
                navController.navigate(
                    route = "${Routes.SATELLITE_DETAIL}/$satelliteId"
                )
            }
        )
        satellitesDetailScreen()
    }
}