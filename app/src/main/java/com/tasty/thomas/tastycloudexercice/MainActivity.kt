package com.tasty.thomas.tastycloudexercice

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.ListView
import com.tasty.thomas.tastycloudexercice.Adapter.ItemListViewAdapter
import com.tasty.thomas.tastycloudexercice.Adapter.ProductGridViewAdapter
import org.json.JSONObject
import com.tasty.thomas.tastycloudexercice.Utils.JsonUtil


class MainActivity : AppCompatActivity() {
    var context: Context = this
    lateinit var productGv: GridView
    lateinit var productGvAdapter: ProductGridViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val products = loadJSONFromAsset("product.json")

        fillMainMenu(products)
        fillProductGridView()

    }

    fun fillMainMenu(jsonProduct: JSONObject) {
        val mainMenu = findViewById<ListView>(R.id.main_menu)
        try {
            val products = jsonProduct.getJSONArray("product")
            val dishTypes = JsonUtil.jsonArrayToStringListPropertyByKey(products, "type")
            val vlou =  ItemListViewAdapter(context, dishTypes)
            mainMenu.adapter = vlou
//            val onList = products.getJSONObject(0).getJSONArray("onlist")
//            val productOnList = JsonUtil.jsonArrayToObjectList(onList, Drink::class.java)
//            ItemListViewAdapter(context, productOnList)
        } catch (e: Exception) {
            System.err.println(e)
        }
    }

    fun fillProductGridView() {
        val list = ArrayList<String>()
        list.add("coucou")
        list.add("zfefe")
        productGv = findViewById(R.id.main_productGrid)
        productGvAdapter = ProductGridViewAdapter(context, list)
        productGv.adapter = productGvAdapter
    }

    fun loadJSONFromAsset(fileName: String): JSONObject {
        val fileText: String = applicationContext.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        return JSONObject(fileText)
        //return result.getJSONArray("product")

        /*val pp = product[0]
        println(product[0])*/
    }
}
