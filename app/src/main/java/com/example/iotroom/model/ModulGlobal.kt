package com.example.iotroom.model

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ModuleGlobal{
    val address_sharedpreferece:String = "com.example.iotroom"

    fun CurrencyFormat(amoune: Double):String{
        val locale = Locale("in", "ID")
        val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(locale)
        return numberFormat.format(amoune)
    }

    fun SaveSharedPreference(context: Context,nameshared:String,valueshared:String){
        val prefs : SharedPreferences = context.getSharedPreferences(address_sharedpreferece,0)
        val editor = prefs.edit()
        editor?.putString(nameshared,valueshared)
        editor?.apply()
    }
    fun GetSharedPreference(context: Context,nameshared:String):String{
        val prefs : SharedPreferences = context.getSharedPreferences(address_sharedpreferece,0)
        val tempname= prefs.getString(nameshared,"")
        return tempname.toString()
    }
    fun HideKeyBoard(context: Context,currentFocus:View){
        val inputManager: InputMethodManager =context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.SHOW_FORCED)
    }

    fun SendHttpData(context: Context, surl:String, sdata: ArrayList<ModelParam>, clickItemSuccess:(response:String)->Unit, clickItemErrorr:(response:String)->Unit){
        val queue = Volley.newRequestQueue(context)
        val postRequest = object :  StringRequest(
            Method.POST, surl,
            Response.Listener<String> { response ->
                clickItemSuccess(response)
            }, Response.ErrorListener {
                clickItemErrorr(it.toString())
            }){
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                for (i in 0 until sdata.count()) {
                    params[sdata[i].namevalue.toString()] = sdata[i].datavalue.toString()
                }
                return params
            }
        }
        queue.add(postRequest)
    }
}

