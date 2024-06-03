package dev.agustacandi.learn.pestsentry.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(ConstVal.PREFS_NAME, Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    val getToken = prefs.getString(ConstVal.KEY_TOKEN, "")
    val getName = prefs.getString(ConstVal.KEY_NAME, "")
    val getOnboardingScreen = prefs.getBoolean(ConstVal.KEY_ONBOARDING_SCREEN, false)

//    fun setLoginPrefs(loginResult: LoginResult) {
//        editor.putString(ConstVal.KEY_NAME, loginResult.name)
//        editor.putString(ConstVal.KEY_TOKEN, loginResult.token)
//        editor.apply()
//    }

    fun setOnboardingScreenPreference() {
        editor.putBoolean(ConstVal.KEY_ONBOARDING_SCREEN, true)
        editor.apply()
    }

    fun clearAllPreferences() {
        editor.remove(ConstVal.KEY_TOKEN)
        editor.remove(ConstVal.KEY_NAME)
        editor.apply()
    }
}