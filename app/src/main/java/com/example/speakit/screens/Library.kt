package com.example.speakit.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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