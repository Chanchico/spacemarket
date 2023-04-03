package com.epsi.spacemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProductInfoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_info)

        val name = intent.extras?.getString("product_name")
        val description = intent.extras?.getString("product_description")
        val image = intent.extras?.getString("product_image")

        val imageViewProduct = findViewById<ImageView>(R.id.image_product)
        Picasso.get().load(image).into(imageViewProduct)

        val descriptionProduct = findViewById<TextView>(R.id.textDescription)
        descriptionProduct.text = description

        setHeaderTitle(name)
        showBack()

    }
}