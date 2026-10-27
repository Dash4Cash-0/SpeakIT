package com.example.speakit.screens

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speakit.R
import com.example.speakit.ui.theme.SpeakITTheme

@Composable
fun Register(
    onBack: () -> Unit
){

    RegisterContent(
        onBack = onBack

    )
}

@Composable
fun RegisterContent(
    onBack: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
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
            Text(text = stringResource( R.string.register_account),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)

        }
    }

}

@Preview(showBackground = true, apiLevel = 36, name = "Register Preview")
@Composable
fun RegisterPreview(){
    SpeakITTheme{
        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                RegisterContent(
                    onBack = {}
                )
            }
        }
    }
}