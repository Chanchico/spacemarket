package com.epsi.spacemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class InfoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        setHeaderTitle("Traversi")
        showBack()
    }
}