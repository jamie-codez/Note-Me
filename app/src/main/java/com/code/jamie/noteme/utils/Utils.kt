package com.code.jamie.noteme.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class Utils {
    companion object {
        private const val pref = "noteme"
        const val jwt_pref = "jwt"
        private const val email_pref = "email"

        @JvmStatic
        fun getPrefs(context: Context): SharedPreferences =
            context.getSharedPreferences(pref, Context.MODE_PRIVATE)

        @JvmStatic
        fun getEditor(sharedPreferences: SharedPreferences): Editor =
            sharedPreferences.edit()

        @JvmStatic
        fun setJWT(jwt: String, editor: Editor) {
            editor.putString(jwt_pref, jwt)
        }

        @JvmStatic
        fun setEmail(email: String, editor: Editor) {
            editor.putString(jwt_pref, email)
        }

        @JvmStatic
        fun getJWT(jwt: String, sharedPreferences: SharedPreferences): String =
            sharedPreferences.getString(jwt_pref, "")!!

        @JvmStatic
        fun getEmail(sharedPreferences: SharedPreferences): String =
            sharedPreferences.getString(email_pref, "")!!

        fun snackBar(view: View, message: String): Snackbar =
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).apply {
                animationMode = Snackbar.ANIMATION_MODE_SLIDE
                show()
            }

        fun toast(context: Context, message: String): Toast =
            Toast.makeText(context, message, Toast.LENGTH_LONG).apply {
                show()
            }
    }
}