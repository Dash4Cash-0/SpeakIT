// Top-level build file where you can add configuration options common to all sub-projects/modules.

// AGP 9 bundles Kotlin Gradle Plugin 2.2.10 for its built-in Kotlin support.
// Putting newer KGP/KSP versions on the classpath here makes AGP use them instead.
buildscript {
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.ksp.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
