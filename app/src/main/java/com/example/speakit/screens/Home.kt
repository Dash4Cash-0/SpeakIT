package com.example.speakit.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speakit.R
import com.example.speakit.viewmodel.HomeViewModel

@Composable
fun Home(viewModel: HomeViewModel = viewModel()) {
    val transcription by viewModel.transcript.collectAsState()
    val translation by viewModel.translation.collectAsState()
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

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Column {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier
                    .size(150.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(text = "TESTING",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
            transcription?.let { Text(it) }
            translation?.let { Text(it) }
            error?.let { Text(it) }
        }
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
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .size(75.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            border = BorderStroke(1.dp, Color.Blue)
        ) {
            Icon(
                painter = if (isRecording) rememberVectorPainter(Icons.Default.Stop) else painterResource(R.drawable.mic_color),
                contentDescription = if (isRecording) "Stop recording" else "Start recording",
                tint = Color.Unspecified
            )
        }
    }
}