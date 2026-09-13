package com.example.speakit.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = "SpeakIT", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(text = "Day 12", fontSize = 12.sp, fontWeight = FontWeight.Bold,
                    color = Color(0xFFD32F2F),
                    modifier = Modifier.
                    background(color = Color(0xFFFFCDD2).copy(alpha = 0.5f), shape = CircleShape)
                        .padding(horizontal = 8.dp))

            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFf8f4f4)
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(text = "\uD83C\uDDEC\uD83C\uDDE7 English", fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(4.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color(0xFFD32F2F),
                            modifier = Modifier
                                .background(color = Color(0xFFFFCDD2).copy(alpha = 0.5f), shape = CircleShape)
                                .padding(8.dp)
                                .size(15.dp))
                        Text(text = "Italian \uD83C\uDDEE\uD83C\uDDF9", fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(4.dp))
                    }

                }
            }
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFf8f4f4)
                    ),
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(top = 15.dp)
                ) {
                    val hasTranscription = !transcription.isNullOrBlank()
                    Text(
                        text = transcription?.takeUnless { it.isBlank() }
                            ?: "What do you want translated today?",
                        color = if (hasTranscription) {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        },
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            var expanded by remember { mutableStateOf(false) }
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFf8f4f4)
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 15.dp)
                    .clickable(
                        onClick = {expanded = !expanded}
                    )

            ) {
                Column(

                ) {
                    Text(
                        text = "Also try * X Forms",
                        modifier = Modifier.padding(8.dp)
                    )
                    if (expanded) {
                        Text(text = "Example 1")
                        Text(text = "Example 2")
                        Text(text = "Example 3")
                        Text(text = "Example 4")
                        Text(text = "Example 5")
                    }
                }

            }
                /*Column {
                    translation?.let { Text(it) }
                    error?.let { Text(it) }
                }*/
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Text(text = "Tap and say a sentence")
                    //translation?.let { Text(it) }
                    /*val micButtonScale by animateFloatAsState(
                        targetValue = if (isRecording) 0.9f else 1f,
                        label = "micButtonScale"
                    )
                    val micButtonElevation by animateDpAsState(
                        targetValue = if (isRecording) 0.dp else 8.dp,
                        label = "micButtonElevation"
                    )
                    val micButtonColor by animateColorAsState(
                        targetValue = if (isRecording) Color(0xFFB71C1C) else Color.Red,
                        label = "micButtonColor"
                    )*/
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
                            .padding(bottom = 5.dp)
                            .size(60.dp),
                            //.graphicsLayer(scaleX = micButtonScale, scaleY = micButtonScale),
                        //colors = ButtonDefaults.buttonColors(containerColor = micButtonColor),
                        border = BorderStroke(1.dp, Color.Red),
                        contentPadding = PaddingValues(0.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            //defaultElevation = micButtonElevation,
                            pressedElevation = 0.dp
                        )
                    ) {
                        Icon(
                            painter = if (isRecording) rememberVectorPainter(Icons.Default.Stop) else painterResource(R.drawable.mic_color),
                            contentDescription = if (isRecording) "Stop recording" else "Start recording",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(25.dp)
                        )

                }
                 }
        }
}