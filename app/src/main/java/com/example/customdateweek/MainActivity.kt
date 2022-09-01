package com.example.customdateweek

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val infoWeek = findViewById<CustomItemInfoWeek>(R.id.itemWeek)
        infoWeek.renderComponent()
        infoWeek.clickCallback = {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }
    }
}