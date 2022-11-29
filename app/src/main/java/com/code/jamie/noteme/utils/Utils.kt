package com.code.jamie.noteme.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class Utils {
    companion object {
        const val pref = "noteme"
        const val jwt_pref = "jwt"
    }

    fun getPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(pref, Context.MODE_PRIVATE)

    fun getEditor(sharedPreferences: SharedPreferences): Editor =
        sharedPreferences.edit()

    fun setJWT(jwt: String, editor: Editor) {
        editor.putString(jwt_pref, jwt)
    }

    fun getJWT(jwt: String, sharedPreferences: SharedPreferences): String =
        sharedPreferences.getString(jwt_pref, "")!!
    fun snackBar(view:View,message:String):Snackbar=
        Snackbar.make(view,message,Snackbar.LENGTH_LONG).apply {
            animationMode = Snackbar.ANIMATION_MODE_SLIDE
            show()
        }
    fun toast(context: Context,message: String):Toast=
        Toast.makeText(context,message,Toast.LENGTH_LONG).apply {
            show()
        }
}