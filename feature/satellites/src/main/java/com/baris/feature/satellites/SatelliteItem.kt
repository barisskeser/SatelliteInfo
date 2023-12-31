package com.baris.feature.satellites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baris.domain.model.Satellite

/**
 * Created on 31.12.2023.
 * @author Barış Keser
 */

@Composable
fun SatelliteItem(
    modifier: Modifier = Modifier,
    satellite: Satellite,
    isLast: Boolean,
    onClick: ((Int, String) -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 8.dp)
            .clickable {
                if(satellite.isActive)
                    onClick?.invoke(satellite.id, satellite.name)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_dot),
                tint = if (satellite.isActive) Color.Green else Color.Red,
                contentDescription = "Satellite status icon"
            )

            Spacer(modifier = Modifier.width(24.dp))

            Column{
                val textColor = if(satellite.isActive) Color.Black else Color.LightGray
                Text(
                    text = satellite.name,
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if(satellite.isActive) "Activate" else "Passive",
                    color = textColor
                )
                MaterialTheme.typography.bodyLarge
            }
        }
        if(!isLast)
            Divider(modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview
@Composable
fun SatelliteItemActivePreview() {
    val mock = Satellite(
        id = 1,
        isActive = true,
        name = "Starship-1"
    )
    SatelliteItem(satellite = mock, isLast = false)
}

@Preview
@Composable
fun SatelliteItemPassivePreview() {
    val mock = Satellite(
        id = 1,
        isActive = false,
        name = "Starship-1"
    )
    SatelliteItem(satellite = mock, isLast = false)
}