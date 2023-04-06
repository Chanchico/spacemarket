package com.epsi.spacemarket

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epsi.spacemarket.adapters.RayonAdapter
import com.epsi.spacemarket.model.Rayon
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class RayonWSActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rayon_wsactivity)

        showBack()
        setHeaderTitle("Rayons")

        val rayons = arrayListOf<Rayon>()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val rayonAdapter = RayonAdapter(rayons)
        recyclerView.adapter = rayonAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL = "https://www.ugarit.online/epsi/categories.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()


        okHttpClient.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("MarketPlace", "############## " + e.message.toString())
            }

            override fun onResponse(call: Call, response: Response){
                //code for rayon response
                val data = response.body?.string()
                Log.e("MarketPlace", "################# response.code:" + response.code)
                if (data != null && response.code == 200) {
                    Log.e("MarketPlace", data)
                    val jsRayons = JSONObject(data)
                    val jsArrayRayons = jsRayons.getJSONArray("items")
                    for (i in 0 until jsArrayRayons.length()) {
                        val js = jsArrayRayons.getJSONObject(i)
                        val rayon = Rayon(
                            js.optString("title", "Not found"),
                            js.optString("products_url", "Not found"),
                        )
                        rayons.add(rayon)
                        runOnUiThread(Runnable {
                            rayonAdapter.notifyDataSetChanged()
                        })
                    }
                }
            }
        })

        recyclerView.setOnClickListener(){}
    }
}