package com.example.iotroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iotroom.model.ModuleGlobal
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    val moduleGlobal = ModuleGlobal()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        btnSaveSettings.setOnClickListener { SaveSettings() }

        edtIPServer.setText( moduleGlobal.GetSharedPreference(this,"ipserver"))
        edtNameSwitch1.setText( moduleGlobal.GetSharedPreference(this,"nameswitch1"))
        edtNameSwitch2.setText( moduleGlobal.GetSharedPreference(this,"nameswitch2"))
    }

    private fun SaveSettings() {
        moduleGlobal.SaveSharedPreference(this,"ipserver",edtIPServer.text.toString())
        moduleGlobal.SaveSharedPreference(this,"nameswitch1",edtNameSwitch1.text.toString())
        moduleGlobal.SaveSharedPreference(this,"nameswitch2",edtNameSwitch2.text.toString())
        Toast.makeText(this,"Berhasil disimpan",Toast.LENGTH_SHORT).show()
        finish()
    }
}
