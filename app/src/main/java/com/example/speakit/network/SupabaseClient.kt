package com.example.speakit.network

import com.example.speakit.BuildConfig
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.createSupabaseClient
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

val supabase = createSupabaseClient(
    supabaseUrl = BuildConfig.SUPABASE_URL,
    supabaseKey = BuildConfig.SUPABASE_PUBLISHABLE_KEY
) {
    install(Auth)
}

suspend fun registerNewUser(userName: String, userEmail: String, userPassword: String) {
    supabase.auth.signUpWith(Email) {
        email = userEmail
        password = userPassword
        data = buildJsonObject { put("username", userName) }
    }
}
