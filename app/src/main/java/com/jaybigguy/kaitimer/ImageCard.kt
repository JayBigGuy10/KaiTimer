package com.jaybigguy.kaitimer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun ImageCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {



//                AssistChip(
//                    onClick = { },
//                    colors = AssistChipDefaults.assistChipColors(
//                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
//                    ),
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Outlined.FavoriteBorder,
//                            contentDescription = null
//                        )
//                    },
//                    label = {
//                        Text(text = "Mark as favorite")
//                    }
//                )
//                AssistChip(
//                    onClick = { },
//                    colors = AssistChipDefaults.assistChipColors(
//                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
//                    ),
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Outlined.Share,
//                            contentDescription = null
//                        )
//                    },
//                    label = {
//                        Text(text = "Share with others")
//                    }
//                )
            }
        }
    }
}