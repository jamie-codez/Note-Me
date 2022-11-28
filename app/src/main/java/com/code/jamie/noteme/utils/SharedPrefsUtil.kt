package com.code.jamie.noteme.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsUtil {
    companion object {
        private val TAG = SharedPrefsUtil::class.java.simpleName
        private const val JWT = "jwt"
        private const val NOTE_ME_PREFS = "note_me_prefs"

        private fun getSharedPrefs(context: Context): SharedPreferences {
            return context.getSharedPreferences(TAG, Context.MODE_PRIVATE)
        }

        private fun getNoteMePrefs(context: Context): SharedPreferences {
            return context.getSharedPreferences(NOTE_ME_PREFS, Context.MODE_PRIVATE)
        }

        @JvmStatic
        fun clearSharedPrefs(context: Context) {
            getSharedPrefs(context).edit().clear().apply()
        }

        @JvmStatic
        fun clearNoteMePrefs(context: Context) {
            getNoteMePrefs(context).edit().clear().apply()
        }

        @JvmStatic
        fun setCurrentJWT(context: Context, label: String, id: String) {
            val editor = getNoteMePrefs(context).edit()
            editor.putString(JWT + label.lowercase(), id)
                .apply()
        }

        @JvmStatic
        fun getCurrentJWT(context: Context, label: String): String? =
            getNoteMePrefs(context).getString(JWT + label.lowercase(), null)
    }
}