package com.example.speakit.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speakit.R
import com.example.speakit.ui.theme.SpeakITTheme
import com.example.speakit.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun Login(
    viewModel: LoginViewModel = viewModel()
){

    LoginContent(

    )
}

@Composable
fun LoginContent(){

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(R.string.app_name), fontSize = 15.sp, fontWeight = FontWeight.Bold)

            Text("English · " +
                    "Português · " +
                    "Italiano · " +
                    "Español · " +
                    "Deutsch · " +
                    "Français · " +
                    "Nederlands · " +
                    "Svenska · " +
                    "日本語 · " +
                    "한국어 · " +
                    "中文（普通话)",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = Modifier.width(120.dp).basicMarquee(
                        iterations = Int.MAX_VALUE,
                        velocity = 20.dp,
                        repeatDelayMillis = 0,
                        spacing = MarqueeSpacing(16.dp)
                    ))
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
                RotatingPhrase()
            }
        }

        Text("Welcome to SpeakIT",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp, modifier =
                Modifier.padding(top = 15.dp),
            color = Color.Black)
        Text("Sign in to begin speaking your next language",
            fontSize = 14.sp, modifier = Modifier.padding(top = 15.dp))


        var email by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            singleLine = true,
            onValueChange = {email = it},
            placeholder = {Text("you@youremail.com")},
            label = {Text("Email")},
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Black

            ),
            shape = CircleShape
        )

        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            singleLine = true,
            placeholder = {Text("Password")},
            label = {Text("Password")},
            onValueChange = {password = it},
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Black

            ),
            shape = CircleShape,
            visualTransformation = if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(if (passwordVisible) Icons.Default.VisibilityOff
                    else Icons.Default.Visibility,
                        contentDescription = "Passoword Visibility")


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
                Text("SIGN IN",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp)
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Sign in arrow")
            }
        }
    }

}

private data class ShowcasePhrase(
    val from: String,
    val to: String
)

private val showcasePhrases = listOf(
    ShowcasePhrase("Could I have a coffee please?", "¿Me pone un café, por favor?"),
    ShowcasePhrase("Wo ist der Bahnhof?", "Where is the station?"),
    ShowcasePhrase("Où sont les toilettes ?", "Var är toaletten?"),
    ShowcasePhrase("How much does this cost?", "これはいくらですか？"),
)

@Composable
private fun RotatingPhrase(modifier: Modifier = Modifier){
    var index by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(4000.milliseconds)
            index = (index + 1) % showcasePhrases.size
        }
    }
    AnimatedContent(
        targetState = showcasePhrases[index],
        transitionSpec = { fadeIn(tween(600)) togetherWith fadeOut(tween(600)) },
        label = "showcasePhrase",
        modifier = modifier
    ) {
        phrase ->
        Column {
            Text(text = stringResource(R.string.login_you_say),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = Color.White.copy(alpha = 0.8f))

            Text(phrase.from, modifier = Modifier.padding(vertical = 8.dp),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold)

            Text(text = stringResource(R.string.login_you_hear),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = Color.White.copy(alpha = 0.8f))

            Text(phrase.to, modifier = Modifier.padding(vertical = 8.dp),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold)
        }
    }
}




@Preview(showBackground = true, apiLevel = 36, name = "Login Preview")
@Composable
fun LoginPreview(){
    SpeakITTheme{
        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                LoginContent(

                )
            }
        }
    }
}