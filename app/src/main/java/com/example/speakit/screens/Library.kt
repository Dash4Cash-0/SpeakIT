package com.example.speakit.screens

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.speakit.navigation.BottomNavbar
import com.example.speakit.ui.theme.SpeakITTheme
import com.example.speakit.viewmodel.LibraryViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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
            items(List(30) {"This is a Test sentence with the number $it"}) { sentence ->

            SavedSentenceCard(
                sentence = sentence,
                translation = "Jag ska bara testa om det fungerar",
                category = "TRAVEL",
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
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = sentence,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = translation,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = category,
                        fontSize = 10.sp,
                        color = Color(0xFF49040A),
                        modifier = Modifier.background(Color(0xFFFFCDD2).copy(alpha = 0.5f), shape = CircleShape).padding(horizontal = 10.dp)
                    )
                    Text(
                        text = formatDateAdded(dateAdded),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
            IconButton(
                onClick = onPlay,
                modifier = Modifier.padding(top = 10.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0xFFFFCDD2).copy(alpha = 0.5f),
                    contentColor = Color(0xFFC62828)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play"
                )
            }
        }
    }

}

private fun formatDateAdded(millis: Long): String{
    val zone = ZoneId.systemDefault()
    val date = Instant.ofEpochMilli(millis).atZone(zone).toLocalDate()
    val today = LocalDate.now(zone)

    return when (date) {
        today -> "TODAY"
        today.minusDays(1) -> "YESTERDAY"
        else -> date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
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