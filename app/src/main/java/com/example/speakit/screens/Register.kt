package com.example.speakit.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.speakit.ui.theme.SpeakITTheme

@Composable
fun Register(){

    RegisterContent(

    )
}

@Composable
fun RegisterContent(){

}

@Preview(showBackground = true, apiLevel = 36, name = "Register Preview")
@Composable
fun RegisterPreview(){
    SpeakITTheme{
        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                RegisterContent(

                )
            }
        }
    }
}