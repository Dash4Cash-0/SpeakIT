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
fun ChangeSourceLanguageDialog(
    onDismiss: () -> Unit,
    onSelect:(String) -> Unit)
{
    Dialog(onDismissRequest = onDismiss) {
        Card(shape = RoundedCornerShape(16.dp)) {
            Column(modifier = Modifier.padding(16.dp). verticalScroll(rememberScrollState())) {
                Text(text = "Choose Language", fontWeight = FontWeight.Bold)
                listOf("English", "Italian", "Swedish").forEach { lang ->
                    TextButton(onClick = {onSelect(lang); onDismiss()}) { Text(lang) }
                }
            }
        }
    }




}