package com.example.speakit.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.speakit.navigation.BottomNavbar
import com.example.speakit.ui.theme.SpeakITTheme
import com.example.speakit.viewmodel.HomeViewModel

@Composable
fun Home(viewModel: HomeViewModel = viewModel()) {
    val transcription by viewModel.transcript.collectAsState()
    //val translation by viewModel.translation.collectAsState()
    //val error by viewModel.error.collectAsState()
    val sourceLanguage by viewModel.sourceLanguage.collectAsState()
    val targetLanguage by viewModel.targetLanguage.collectAsState()
    val context = LocalContext.current
    val isRecording by viewModel.isRecording.collectAsState()
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            viewModel.startRecording()
        }
    }

    HomeContent(
        transcription = transcription,
        isRecording = isRecording,
        sourceLanguage = sourceLanguage,
        targetLanguage = targetLanguage,
        onMicClick = {
            if (isRecording) {
                viewModel.stopRecording()
            } else {
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
        }
    )
}

@Composable
fun HomeContent(
    transcription: String?,
    isRecording: Boolean,
    sourceLanguage: String?,
    targetLanguage: String?,
    onMicClick: () -> Unit
) {
        val sentenceForms = mutableListOf(
            "Example Sentence 1",
            "Example Sentence Number 2",
            "Example Sentence Again")

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
                        Text(text = "\uD83C\uDDEC\uD83C\uDDE7 $sourceLanguage", fontSize = 15.sp,
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
                        Text(text = "$targetLanguage \uD83C\uDDEE\uD83C\uDDF9", fontSize = 15.sp,
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
                    Text(text = "YOU SAID",
                        modifier = Modifier
                            .padding(top = 12.dp, start = 15.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp)
                    val hasTranscription = !transcription.isNullOrBlank()
                    Text(
                        text = transcription?.takeUnless { it.isBlank() }
                            ?: if (isRecording) "Listening..." else "What do you want translated today?",
                        color = if (hasTranscription) {
                            Color.Black
                        } else {
                            Color.Black.copy(alpha = 0.6f)
                        },
                        modifier = Modifier.padding(16.dp),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Start
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
                    modifier = Modifier.padding(5.dp)

                ) {
                    Text(
                        text = "Also try \u2022 ${sentenceForms.size} Forms",
                        modifier = Modifier.padding(8.dp)
                    )
                    if (expanded) {
                        sentenceForms.forEach { sentence ->
                            Text(sentence)
                        }
                    }
                }

            }
                /*Column {
                    translation?.let { Text(it) }
                    error?.let { Text(it) }
                }*/
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                ) {
                    Text(text = if(isRecording)" Listening - Tap to stop" else "Tap and say a sentence",
                        modifier = Modifier.padding(top = 20.dp) )
                    //translation?.let { Text(it) }
                    val micButtonScale by animateFloatAsState(
                        targetValue = if (isRecording) 0.9f else 1f,
                        label = "micButtonScale"
                    )
                    val micButtonElevation by animateDpAsState(
                        targetValue = if (isRecording) 0.dp else 8.dp,
                        label = "micButtonElevation"
                    )
                    val micButtonColor by animateColorAsState(
                        targetValue = if (isRecording) Color(0xFFB71C1C) else Color(0xFFdd2b0f),
                        label = "micButtonColor"
                    )
                    Button(
                        onClick = onMicClick,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .size(60.dp)
                            .graphicsLayer(scaleX = micButtonScale, scaleY = micButtonScale),
                        colors = ButtonDefaults.buttonColors(containerColor = micButtonColor),
                        border = BorderStroke(1.dp, Color.Red),
                        contentPadding = PaddingValues(0.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = micButtonElevation,
                            pressedElevation = 0.dp
                        )
                    ) {
                        Icon(
                            painter = if (isRecording) rememberVectorPainter(Icons.Default.Stop) else rememberVectorPainter(Icons.Default.Mic),
                            contentDescription = if (isRecording) "Stop recording" else "Start recording",
                            modifier = Modifier.size(25.dp)
                        )

                }
                 }
        }
}

@Preview(showBackground = true, apiLevel = 36)
@Composable
fun HomePreview() {
    SpeakITTheme {
        HomeContent(
            transcription = null,
            isRecording = false,
            sourceLanguage = "English",
            targetLanguage = "Italian",
            onMicClick = {}
        )
    }
}

@Preview(showBackground = true, apiLevel = 36, name = "Recording")
@Composable
fun HomeRecordingPreview() {
    SpeakITTheme {
        HomeContent(
            transcription = "Buongiorno, come stai?",
            isRecording = true,
            sourceLanguage = "English",
            targetLanguage = "Italian",
            onMicClick = {}
        )
    }
}

@Preview(showBackground = true, apiLevel = 36, name = "With Nav Bar")
@Composable
fun HomeWithNavBarPreview() {
    SpeakITTheme {
        Scaffold(
            bottomBar = { BottomNavbar(rememberNavController()) }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                HomeContent(
                    transcription = null,
                    isRecording = false,
                    sourceLanguage = "English",
                    targetLanguage = "Italian",
                    onMicClick = {}
                )
            }
        }
    }
}