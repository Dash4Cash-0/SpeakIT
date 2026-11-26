package com.example.speakit.network

import com.example.speakit.BuildConfig
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.createSupabaseClient

val supabase = createSupabaseClient(
    supabaseUrl = BuildConfig.SUPABASE_URL,
    supabaseKey = BuildConfig.SUPABASE_PUBLISHABLE_KEY
) {
    install(Auth)
}

suspend fun registerNewUser(userEmail: String, userPassword: String) {
    supabase.auth.signUpWith(Email){
        email = userEmail
        password = userPassword
    }
}