 package com.epsi.spacemarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

 class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setHeaderTitle("Spacemarket")

        val buttonInfo = findViewById<Button>(R.id.buttonInfo)
        buttonInfo.setOnClickListener {
            val intent =  Intent(application, InfoActivity::class.java)
            startActivity(intent)
        }
    }
}