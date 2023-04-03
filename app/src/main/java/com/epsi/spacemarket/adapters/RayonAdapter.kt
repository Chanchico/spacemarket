package com.epsi.spacemarket.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epsi.spacemarket.ProductWSActivity
import com.epsi.spacemarket.R
import com.epsi.spacemarket.model.Rayon

class RayonAdapter(val rayons: ArrayList<Rayon>) :
    RecyclerView.Adapter<RayonAdapter.ViewHolder>()
    {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                RayonAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_rayon, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: RayonAdapter.ViewHolder, position: Int) {
            val rayon = rayons.get(position)
            holder.rayonName.text = rayon.name
            holder.rayonProductsUrl.text = rayon.products_url

            holder.layoutContent.setOnClickListener(View.OnClickListener {
               val intent = Intent(holder.rayonName.context, ProductWSActivity::class.java)
                intent.putExtra("nameCat", rayon.name)
                intent.putExtra("url", rayon.products_url)
                holder.rayonName.context.startActivity(intent)
            })
        }

        override fun getItemCount(): Int {
            return rayons.size
        }


        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val rayonName = view.findViewById<TextView>(R.id.rayonName)
            val rayonProductsUrl = view.findViewById<TextView>(R.id.rayonProductsUrl)
            val layoutContent = view.findViewById<LinearLayout>(R.id.layoutContent)
        }
    }
