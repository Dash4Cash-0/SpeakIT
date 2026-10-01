package com.example.speakit.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.speakit.navigation.BottomNavbar
import com.example.speakit.ui.theme.SpeakITTheme
import com.example.speakit.viewmodel.LibraryViewModel

@Composable
fun Library(viewModel: LibraryViewModel = viewModel()) {


    LibraryContent(

    )


}


@Composable
fun LibraryContent(){

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

                    )
            }
        }
    }
}