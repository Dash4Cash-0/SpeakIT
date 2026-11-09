package com.example.speakit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speakit.R
import com.example.speakit.components.FormScreen
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
    FormScreen  {
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

        Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.05f))

        Text(
            text = stringResource(R.string.register_freeNoCard),
            fontSize = 10.sp,
            color = Color(0xFF49040A),
            modifier = Modifier
                .background(Color(0xFFFFCDD2).copy(alpha = 0.5f), shape = CircleShape)
                .padding(horizontal = 10.dp))

        Text(modifier = Modifier.padding(top = 12.dp),
            text = stringResource(R.string.register_startSpeakingText),
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black)
        Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.05f))
            val nameState = rememberTextFieldState()
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                state = nameState,
                lineLimits = TextFieldLineLimits.SingleLine,
                placeholder = {Text("Your first name")},
                label = {Text("Name")},
                labelPosition = TextFieldLabelPosition.Attached(alwaysMinimize = true),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Black

                ),
                shape = CircleShape
            )
            val emailState = rememberTextFieldState()
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp),
                state = emailState,
                lineLimits = TextFieldLineLimits.SingleLine,
                placeholder = {Text("you@example.com")},
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
            val passwordState = rememberTextFieldState()
            var passwordVisible by remember { mutableStateOf(false) }
            OutlinedSecureTextField(
                modifier = Modifier.fillMaxWidth(),
                state = passwordState,
                placeholder = {Text("***********")},
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
        Column(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(text = "\u2713 At least 8 characters", fontSize = 10.sp)
            Text(text = "\u2713 At least one number", fontSize = 10.sp)
        }
        Row(
           modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var accepted by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (accepted) Color.Red else Color.White)
                    .border(width = 1.5.dp, color = Color.Red, shape = RoundedCornerShape(6.dp))
                    .toggleable(value = accepted, role = Role.Checkbox, onValueChange = {accepted = it}),
                contentAlignment = Alignment.Center
            ) {
                if (accepted) {
                    Icon(Icons.Default.Check, contentDescription = null,
                        tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
            val linkStyle = TextLinkStyles(
                style = SpanStyle(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            )
            Text(
                text = buildAnnotatedString {
                    append("I agree to the ")
                    withLink(LinkAnnotation.Clickable(tag = "terms", styles = linkStyle) {
                        // TODO: open Terms
                    }) {
                        append("Terms")
                    }
                    append(" and ")
                    withLink(LinkAnnotation.Clickable(tag = "privacy", styles = linkStyle) {
                        // TODO: open Privacy Policy
                    }) {
                        append("Privacy Policy")
                    }
                },
                fontSize = 12.sp
            )
        }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
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
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            onClick = {},
            shape = CircleShape
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.register_signupGoogle),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "New to SpeakIT?", fontSize = 14.sp)
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Red,
                    containerColor = Color.Transparent
                ),
                onClick = {}
            ) {
                Text(text = "Create an account")
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