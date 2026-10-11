package com.example.speakit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speakit.ui.theme.SpeakITTheme
import com.example.speakit.viewmodel.SentenceDetailViewModel

@Composable
fun SentenceDetails(
    sentenceId: Long,
    onBack: () -> Unit,
    viewModel: SentenceDetailViewModel = viewModel()
){

    SentenceDetailsContent(
        onBack = onBack

    )
}

@Composable
fun SentenceDetailsContent(
    onBack: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    shadowElevation = 4.dp,
                    tonalElevation = 2.dp
                ) {

                    IconButton(
                        onClick = onBack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
                Text("Sentence",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold)
            }
            Text(text = "SAVED", fontSize = 12.sp, fontWeight = FontWeight.Bold,
                color = Color(0xFFD32F2F),
                modifier = Modifier.
                background(color = Color(0xFFFFCDD2).copy(alpha = 0.5f), shape = CircleShape)
                    .padding(horizontal = 8.dp))
        }
        ElevatedCard(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFC62828)
            )

        ) {
            Column(
                modifier = Modifier.padding(12.dp),

            )
            {
                Text("Italian",
                    color = Color.White)

                Text("Potrei avere un caffè, perfavore?",
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold)

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf("▶ HEAR IT", "0.7x", "REPEAT").forEach { title ->
                        SentenceCardButtons(
                            label = title,
                            onClick = {}
                        )
                    }

                }
            }


        }
        ElevatedCard(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                Text("YOU SAID • ENGLISH", fontSize = 10.sp)
                Text("Could i have a coffee, please?",
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}
@Composable
private fun SentenceCardButtons(
    label: String,
    onClick: () -> Unit
){
    TextButton(
        modifier = Modifier.padding(8.dp),
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(2.dp),
        colors = ButtonColors(
            containerColor = Color.White,
            contentColor = Color.Red,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified)
    ) {
        Text(text = label, fontWeight = FontWeight.Bold)
    }

}



@Preview(showBackground = true, apiLevel = 36, name = "Library preview")
@Composable
fun SentenceDetailsPreview(){
    SpeakITTheme{
        Scaffold(
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                SentenceDetailsContent(
                    onBack = {}

                )
            }
        }
    }
}
