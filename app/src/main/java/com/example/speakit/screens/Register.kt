package com.example.speakit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speakit.R
import com.example.speakit.components.FormScreen
import com.example.speakit.ui.theme.SpeakITTheme
import com.example.speakit.viewmodel.RegisterViewModel

@Composable
fun Register(viewModel: RegisterViewModel = viewModel(),
    onBack: () -> Unit,
    onExistingAccount: () -> Unit,
){
    val uiState by viewModel.uiState.collectAsState()


    RegisterContent(
        onBack = onBack,
        onExistingAccount = onExistingAccount,
        onRegister = viewModel::registerUser,
        uiState = uiState

    )
}

@Composable
fun RegisterContent(
    onBack: () -> Unit,
    onExistingAccount: () -> Unit,
    onRegister: (String, String, String) -> Unit,
    uiState: RegisterViewModel.RegisterUiState

){
    FormScreen  {
        val nameState = rememberTextFieldState()
        val emailState = rememberTextFieldState()
        val passwordState = rememberTextFieldState()
        var passwordVisible by remember { mutableStateOf(false) }
        var accepted by remember { mutableStateOf(false) }

        val email = emailState.text.toString().trim()
        val password = passwordState.text.toString()

        val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val hasMinLength = password.length >= 8
        val hasNumber = password.any { it.isDigit() }

        val canRegister = nameState.text.isNotBlank() &&
                isEmailValid &&
                hasMinLength &&
                hasNumber &&
                accepted

        val isLoading = uiState is RegisterViewModel.RegisterUiState.Loading
        val isDone = uiState is RegisterViewModel.RegisterUiState.Success

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
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
                        contentDescription = stringResource(R.string.register_back),
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

        Text(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
            text = stringResource(R.string.register_startSpeakingText),
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black)
        Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.05f))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = nameState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text(stringResource(R.string.register_yourName)) },
            label = { Text(stringResource(R.string.register_name)) },
            labelPosition = TextFieldLabelPosition.Attached(alwaysMinimize = true),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Black
            ),
            shape = CircleShape
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp),
            isError = email.isNotEmpty() && !isEmailValid,
            supportingText = {
                if (email.isNotEmpty() && !isEmailValid) Text(stringResource(R.string.register_validEmail))
            },
            state = emailState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text(stringResource(R.string.register_emailPlaceholder)) },
            label = { Text(stringResource(R.string.register_email)) },
            labelPosition = TextFieldLabelPosition.Attached(alwaysMinimize = true),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Black
            ),
            shape = CircleShape
        )
        OutlinedSecureTextField(
            modifier = Modifier.fillMaxWidth(),
            state = passwordState,
            placeholder = { Text(stringResource(R.string.register_passwordPlaceholder)) },
            label = { Text(stringResource(R.string.register_password)) },
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
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff
                            else Icons.Default.Visibility,
                        contentDescription = stringResource(
                            if (passwordVisible) R.string.register_hidePassword
                            else R.string.register_showPassword
                        )
                    )
                }
            }
        )
        Column(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.register_ruleMinLength),
                fontSize = 10.sp,
                color = if (hasMinLength) Color(0xFF2E7D32) else Color.Gray
            )
            Text(
                text = stringResource(R.string.register_ruleNumber),
                fontSize = 10.sp,
                color = if (hasNumber) Color(0xFF2E7D32) else Color.Gray
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
            val agreeTo = stringResource(R.string.register_agreeTo)
            val terms = stringResource(R.string.register_terms)
            val and = stringResource(R.string.register_and)
            val privacyPolicy = stringResource(R.string.register_privacyPolicy)
            Text(
                text = buildAnnotatedString {
                    append("$agreeTo ")
                    withLink(LinkAnnotation.Clickable(tag = "terms", styles = linkStyle) {
                        // TODO: open Terms
                    }) {
                        append(terms)
                    }
                    append(" $and ")
                    withLink(LinkAnnotation.Clickable(tag = "privacy", styles = linkStyle) {
                        // TODO: open Privacy Policy
                    }) {
                        append(privacyPolicy)
                    }
                },
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        when (uiState){
            is RegisterViewModel.RegisterUiState.Error -> Text(
                text = stringResource(uiState.messageRes),
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            RegisterViewModel.RegisterUiState.Success -> Text(
                text = stringResource(R.string.register_checkEmail),
                color = Color(0xFF2E7D32),
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            else -> {}

        }
        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = canRegister && !isLoading && !isDone,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC62828)
            ),
            onClick = {
                onRegister(
                    nameState.text.toString().trim(),
                    email,
                    password
                )
            },
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
                if (isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = LocalContentColor.current
                    )
                }else{
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                }
            }
        }
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            enabled = accepted,
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
            Text(text = stringResource(R.string.register_existingAccount), fontSize = 14.sp)
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Red,
                    containerColor = Color.Transparent
                ),
                onClick = onExistingAccount
            ) {
                Text(text = stringResource(R.string.register_login))
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
                    onBack = {},
                    onExistingAccount = {},
                    onRegister = {_,_,_ -> },
                    uiState = RegisterViewModel.RegisterUiState.Idle
                )
            }
        }
    }
}