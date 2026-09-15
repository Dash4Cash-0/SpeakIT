package com.example.speakit.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import com.example.speakit.R

@OptIn(ExperimentalTextApi::class)
val ArchivoFontFamily = FontFamily(
    Font(
        R.font.archivo,
        weight = FontWeight.Normal,
        variationSettings = FontVariation.Settings(FontVariation.weight(400))
    ),
    Font(
        R.font.archivo,
        weight = FontWeight.Medium,
        variationSettings = FontVariation.Settings(FontVariation.weight(500))
    ),
    Font(
        R.font.archivo,
        weight = FontWeight.SemiBold,
        variationSettings = FontVariation.Settings(FontVariation.weight(600))
    ),
    Font(
        R.font.archivo,
        weight = FontWeight.Bold,
        variationSettings = FontVariation.Settings(FontVariation.weight(700))
    ),
    Font(
        R.font.archivo,
        weight = FontWeight.ExtraBold,
        variationSettings = FontVariation.Settings(FontVariation.weight(800))
    ),
    Font(
        R.font.archivo_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic,
        variationSettings = FontVariation.Settings(FontVariation.weight(400))
    ),
)

private val baseTypography = Typography()

// Material typography styles, using Archivo as the app-wide font family.
val Typography = Typography(
    displayLarge = baseTypography.displayLarge.copy(fontFamily = ArchivoFontFamily),
    displayMedium = baseTypography.displayMedium.copy(fontFamily = ArchivoFontFamily),
    displaySmall = baseTypography.displaySmall.copy(fontFamily = ArchivoFontFamily),
    headlineLarge = baseTypography.headlineLarge.copy(fontFamily = ArchivoFontFamily),
    headlineMedium = baseTypography.headlineMedium.copy(fontFamily = ArchivoFontFamily),
    headlineSmall = baseTypography.headlineSmall.copy(fontFamily = ArchivoFontFamily),
    titleLarge = baseTypography.titleLarge.copy(fontFamily = ArchivoFontFamily),
    titleMedium = baseTypography.titleMedium.copy(fontFamily = ArchivoFontFamily),
    titleSmall = baseTypography.titleSmall.copy(fontFamily = ArchivoFontFamily),
    bodyLarge = baseTypography.bodyLarge.copy(fontFamily = ArchivoFontFamily),
    bodyMedium = baseTypography.bodyMedium.copy(fontFamily = ArchivoFontFamily),
    bodySmall = baseTypography.bodySmall.copy(fontFamily = ArchivoFontFamily),
    labelLarge = baseTypography.labelLarge.copy(fontFamily = ArchivoFontFamily),
    labelMedium = baseTypography.labelMedium.copy(fontFamily = ArchivoFontFamily),
    labelSmall = baseTypography.labelSmall.copy(fontFamily = ArchivoFontFamily)
)