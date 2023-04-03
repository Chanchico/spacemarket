package com.epsi.spacemarket

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epsi.spacemarket.adapters.ProductAdapter
import com.epsi.spacemarket.model.Product
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class ProductWSActivity() : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rayon_wsactivity)
        val nameCat = intent.extras?.getString("nameCat")
        val url = intent.extras?.getString("url")

        showBack()
        setHeaderTitle(nameCat)

        val products = arrayListOf<Product>();

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val productAdapter = ProductAdapter(products)
        recyclerView.adapter = productAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val request = url?.let {
            Request.Builder()
                .url(it)
                .get()
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build()
        }

        if (request != null) {
            okHttpClient.newCall(request).enqueue(object: okhttp3.Callback{
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.e("MarketPlace", "############## " + e.message.toString())
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    val data = response.body?.string()
                    Log.e("MarketPlace", "################# response.code:" + response.code)
                    if (data != null && response.code == 200) {
                        Log.e("MarketPlace", data)
                        val jsRayons = JSONObject(data)
                        val jsArrayRayons = jsRayons.getJSONArray("items")
                        for (i in 0 until jsArrayRayons.length()) {
                            val js = jsArrayRayons.getJSONObject(i)
                            val product = Product(
                                js.optString("name", "Not found"),
                                js.optString("description", "Not found"),
                                js.optString("picture_url", "Not found"),
                            )
                            products.add(product)
                            runOnUiThread(Runnable {
                                productAdapter.notifyDataSetChanged()
                            })
                        }
                    }
                }
            })
        }

    }
}