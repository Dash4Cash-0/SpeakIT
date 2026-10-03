package com.example.speakit.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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

@Composable
fun Library(viewModel: LibraryViewModel = viewModel()) {
    val savedSentences = mutableListOf("") //PLACEHOLDER LIST, PULL FROM DB


    LibraryContent(
        savedSentences = savedSentences

    )


}


@Composable
fun LibraryContent(
    savedSentences: MutableList<String>
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
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            var isSelected = false
            listOf("All", "Travel", "Social", "Animals", "Other").forEach {
                category -> LibraryCategories(
                    label = category,
                    selected = isSelected,
                    onClick = {
                        isSelected = true
                    }
                )
            }
        }
    }

}

@Composable
fun LibraryCategories(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
){
        TextButton(
            modifier = Modifier.padding(vertical = 8.dp),
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


@Preview(showBackground = true, apiLevel = 36, name = "Library preview")
@Composable
fun LibraryPreview(){
    SpeakITTheme{
        Scaffold(
            bottomBar = {BottomNavbar(rememberNavController())}
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                LibraryContent(
                    savedSentences = mutableListOf()
                    )
            }
        }
    }
}