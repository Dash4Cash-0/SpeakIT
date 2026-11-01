package com.example.speakit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

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
            Text(text = stringResource(R.string.app_name),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold)

        }
        Text(
            text = stringResource(R.string.register_freeNoCard),
            fontSize = 10.sp,
            color = Color(0xFF49040A),
            modifier = Modifier.background(Color(0xFFFFCDD2).copy(alpha = 0.5f), shape = CircleShape).padding(horizontal = 10.dp))

        Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.05f))
            Text("NAME")
            val nameState = rememberTextFieldState()
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                state = nameState,
                lineLimits = TextFieldLineLimits.SingleLine,
                placeholder = {Text("you@youremail.com")},
                label = {Text("")},
                labelPosition = TextFieldLabelPosition.Attached(alwaysMinimize = true),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Black

                ),
                shape = CircleShape
            )
            Text("EMAIL")
            val emailState = rememberTextFieldState()
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                state = emailState,
                lineLimits = TextFieldLineLimits.SingleLine,
                placeholder = {Text("you@youremail.com")},
                label = {Text("Email")},
                labelPosition = TextFieldLabelPosition.Attached(alwaysMinimize = true),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Black

                ),
                shape = CircleShape
            )
            Text("PASSWORD")
            val passwordState = rememberTextFieldState()
            var passwordVisible by remember { mutableStateOf(false) }
            OutlinedSecureTextField(
                modifier = Modifier.fillMaxWidth(),
                state = passwordState,
                placeholder = {Text("Password")},
                label = {Text("Password")},
                labelPosition = TextFieldLabelPosition.Attached(alwaysMinimize = true),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Black

                ),
                shape = CircleShape,
                textObfuscationMode = if (passwordVisible) TextObfuscationMode.Visible
                else TextObfuscationMode.RevealLastTyped,
                trailingIcon = {
                    IconButton(onClick = {passwordVisible = !passwordVisible}) {
                        Icon(if (passwordVisible) Icons.Default.VisibilityOff
                        else Icons.Default.Visibility,
                            contentDescription = "Password Visibility")


                    }
                }
            )
        Spacer(modifier = Modifier.weight(1f))
        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC62828)
            ),
            onClick = {},
            shape = CircleShape
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.register_account),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp)
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Register arrow")
            }
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