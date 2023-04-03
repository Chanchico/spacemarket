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



        val name = intent.extras?.getString("productName")
        val description = intent.extras?.getString("productDescription")
        val image = intent.extras?.getString("productImage")

        setHeaderTitle(name)
        showBack()

        val imageViewProduct = findViewById<ImageView>(R.id.imageProduct)
        Picasso.get().load(image).into(imageViewProduct)

        val descriptionProduct = findViewById<TextView>(R.id.textDescription)
        descriptionProduct.text = description

    }
}