package com.epsi.spacemarket

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(){

    fun setHeaderTitle( title:String?){
        val textViewTitle= findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.setText(title)
    }

    fun showBack(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility= View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}