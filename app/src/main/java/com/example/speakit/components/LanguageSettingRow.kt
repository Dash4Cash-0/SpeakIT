package com.example.speakit.components

import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


var languageList = listOf(
    "\uD83C\uDDEC\uD83C\uDDE7 English",
    "\ud83c\uddea\ud83c\uddf8 Spanish",
    "\uD83C\uDDEE\uD83C\uDDF9 Italian",
    "\ud83c\uddf5\ud83c\uddf9 Portuguese",
    "\ud83c\uddeb\ud83c\uddf7 French",
    "\ud83c\udde9\ud83c\uddea German",
    "\ud83c\uddf3\ud83c\uddf1 Dutch",
    "\ud83c\uddf8\ud83c\uddea Swedish",
    "\ud83c\uddef\ud83c\uddf5 Japanese",
    "\ud83c\uddf0\ud83c\uddf7 Korean",
    "\ud83c\udde8\ud83c\uddf3 Chinese (Mandarin)")
@Composable
fun LanguageSettingRow(
    label: String,
    language: String?,
    onChangeClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Text(text = "$label  $language",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp))
        TextButton(onClick = onChangeClick ) { Text("Change") }
    }

}
@Composable
fun ChangeLanguageDialog(
    title: String,
    onDismiss: () -> Unit,
    onSelect:(String) -> Unit
){
    Dialog(onDismissRequest = onDismiss) {
        Card(shape = RoundedCornerShape(16.dp)) {
            Column(modifier = Modifier.padding(16.dp). verticalScroll(rememberScrollState())) {
                Text(text = "Choose Language", fontWeight = FontWeight.Bold)
                languageList.forEach { lang ->
                    TextButton(onClick = {onSelect(lang); onDismiss()}) { Text(lang) }
                }
            }
        }
    }

}