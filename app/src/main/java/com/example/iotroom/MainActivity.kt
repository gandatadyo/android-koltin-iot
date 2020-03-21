package com.example.iotroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotroom.model.ModelParam
import com.example.iotroom.model.ModuleGlobal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val moduleGlobal = ModuleGlobal()
    var ipserver:String = "http://192.168.1.8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSwitch1On.setOnClickListener { Switch("2") }
        btnSwitch1Off.setOnClickListener { Switch("1") }
        btnSwitch2On.setOnClickListener { Switch("4") }
        btnSwitch2Off.setOnClickListener { Switch("3") }
        btnSetting_Main.setOnClickListener { startActivity(Intent(this,SettingsActivity::class.java)) }
    }


    override fun onResume() {
        super.onResume()
        val ipservershared = moduleGlobal.GetSharedPreference(this,"ipserver")
        if(ipservershared!="") ipserver=ipservershared

        val nameswitch1 = moduleGlobal.GetSharedPreference(this,"nameswitch1")
        if(nameswitch1!="") lblSwitch1.text=nameswitch1

        val nameswitch2 = moduleGlobal.GetSharedPreference(this,"nameswitch2")
        if(nameswitch2!="") lblSwitch2.text=nameswitch2

        lblcaptionipserver.text = "Server : $ipserver"
    }

    private fun Switch(mode:String){
        if(mode=="1"){
            val url = ipserver+"?switch1=on"
            val dsParam =  arrayListOf<ModelParam>()
            moduleGlobal.SendHttpData(this,url,dsParam,{ partItem:String-> ResponseData(partItem)},{ partItem:String-> ResponseData(partItem)})
        }else if(mode=="2"){
            val url = ipserver+"?switch1=off"
            val dsParam =  arrayListOf<ModelParam>()
            moduleGlobal.SendHttpData(this,url,dsParam,{ partItem:String-> ResponseData(partItem)},{ partItem:String-> ResponseData(partItem)})
        }else if(mode=="3"){
            val url = ipserver+"?switch2=on"
            val dsParam =  arrayListOf<ModelParam>()
            moduleGlobal.SendHttpData(this,url,dsParam,{ partItem:String-> ResponseData(partItem)},{ partItem:String-> ResponseData(partItem)})
        }else if(mode=="4"){
            val url = ipserver+"?switch2=off"
            val dsParam =  arrayListOf<ModelParam>()
            moduleGlobal.SendHttpData(this,url,dsParam,{ partItem:String-> ResponseData(partItem)},{ partItem:String-> ResponseData(partItem)})
        }
    }

    private fun ResponseData(response:String){
        Toast.makeText(this,response,Toast.LENGTH_SHORT).show()
    }
}
