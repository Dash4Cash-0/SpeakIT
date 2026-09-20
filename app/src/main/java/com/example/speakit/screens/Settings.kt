package com.example.speakit.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speakit.viewmodel.SettingsViewModel


@Composable
fun Settings(viewModel: SettingsViewModel = viewModel()) {

    SettingsContent(

    )

}

@Composable
fun SettingsContent(){
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {

        }
    }

}