package com.example.speakit.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.speakit.navigation.BottomNavbar
import com.example.speakit.ui.theme.SpeakITTheme
import com.example.speakit.viewmodel.LibraryViewModel
import java.sql.Date
import java.sql.Time

@Composable
fun Library(viewModel: LibraryViewModel = viewModel()) {
    val savedSentences = listOf<String>() //PLACEHOLDER LIST, PULL FROM DB
    val chosenCategory by viewModel.categoryChoice.collectAsState()


    LibraryContent(
        savedSentences = savedSentences,
        chosenCategory = chosenCategory,
        onChosenCategory = viewModel::setCategoryChoice

    )


}


@Composable
fun LibraryContent(
    savedSentences: List<String>,
    chosenCategory: String,
    onChosenCategory: (String) -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Library",
            modifier = Modifier.padding(6.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold)
        Text(text = "${savedSentences.size} saved",
            modifier = Modifier.padding(6.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold)
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
        ) {
            listOf("All", "Travel", "Shopping", "Other").forEach {
                category -> LibraryCategories(
                    label = category,
                    selected = chosenCategory == category,
                    onClick = {
                        onChosenCategory(category)
                    }
                )
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {

            SavedSentenceCard(
                sentence = "Test sentence this is a test",
                translation = "Jag ska bara testa om det fungerar",
                category = "Test",
                dateAdded = System.currentTimeMillis(),
                onPlay = {},
                onClick = {}

            )

            }
        }
    }

}

@Composable
private fun LibraryCategories(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
){
        TextButton(
            onClick = onClick,
            colors = ButtonDefaults.textButtonColors(
                containerColor = if(selected) Color(0xFFFFCDD2).copy(alpha = 0.5f) else Color.Transparent,
                contentColor = if(selected) Color.Red else Color.Unspecified
            )
        ) {
            Text(text = label,
                fontWeight = if(selected) FontWeight.Bold else FontWeight.Normal)
        }
}

@Composable
private fun SavedSentenceCard(
    sentence: String,
    translation: String,
    category: String,
    dateAdded: Long,
    onClick: () -> Unit,
    onPlay: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = sentence,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = translation,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = category,
                        fontSize = 8.sp,
                    )
                    Text(
                        text = "$dateAdded",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            IconButton(
                onClick = onPlay,

                ) { }
        }
    }

}


@Preview(showBackground = true, apiLevel = 36, name = "Library preview")
@Composable
fun LibraryPreview(){
    SpeakITTheme{
        Scaffold(
            bottomBar = {BottomNavbar(rememberNavController())}
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                LibraryContent(
                    savedSentences = listOf(),
                    chosenCategory = "All",
                    onChosenCategory = {}
                    )
            }
        }
    }
}