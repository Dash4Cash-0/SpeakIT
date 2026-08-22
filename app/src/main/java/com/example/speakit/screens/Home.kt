package com.example.speakit.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speakit.viewmodel.HomeViewModel

@Composable
fun Home(viewModel: HomeViewModel = viewModel()) {
    val transcription by viewModel.transcript.collectAsState()
    val error by viewModel.error.collectAsState()
    val context = LocalContext.current
    val isRecording by viewModel.isRecording.collectAsState()
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            viewModel.startRecording()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        transcription?.let { Text(it) }
        error?.let { Text(it) }
        Button(
            onClick = {
                if (isRecording) {
                    viewModel.stopRecording()
                    return@Button
                }

                val hasPermission = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED

                if (hasPermission) {
                    viewModel.startRecording()
                } else {
                    permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                }
            }
        ) {
            Icon(
                imageVector = if (isRecording) Icons.Default.Stop else Icons.Default.Mic,
                contentDescription = if (isRecording) "Stop recording" else "Start recording"
            )
        }
    }
}