package com.baris.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */

@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SATELLITES
    ) {
        satellitesScreen(
            onSatelliteClick = { satelliteId, satelliteName ->
                navController.navigate(
                    route = "${Routes.SATELLITE_DETAIL}/$satelliteId/$satelliteName"
                )
            }
        )
        satellitesDetailScreen()
    }
}