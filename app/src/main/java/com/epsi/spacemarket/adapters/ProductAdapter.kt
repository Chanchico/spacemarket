package com.epsi.spacemarket.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epsi.spacemarket.ProductInfoActivity
import com.epsi.spacemarket.R
import com.epsi.spacemarket.model.Product
import com.squareup.picasso.Picasso


class ProductAdapter (val products: ArrayList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val product = products.get(position)
        holder.productName.text = product.name
        holder.productDescription.text = product.description
        Picasso.get().load(product.image).into(holder.productImage)

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.productName.context, ProductInfoActivity::class.java)
            intent.putExtra("productName", product.name)
            intent.putExtra("productDescription", product.description)
            intent.putExtra("productImage", product.image)
            holder.productName.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName = view.findViewById<TextView>(R.id.textName)
        val productDescription = view.findViewById<TextView>(R.id.textDescription)
        val productImage = view.findViewById<ImageView>(R.id.imageProduct)
    }
}


