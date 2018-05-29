package com.tasty.thomas.tastycloudexercice

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.GridView
import android.widget.ListView
import com.tasty.thomas.tastycloudexercice.Adapter.ItemListViewAdapter
import com.tasty.thomas.tastycloudexercice.Adapter.ProductGridViewAdapter
import com.tasty.thomas.tastycloudexercice.Model.Product
import org.json.JSONObject
import com.tasty.thomas.tastycloudexercice.Utils.JsonUtil
import android.R.attr.fragment
import android.support.v4.app.Fragment
import com.tasty.thomas.tastycloudexercice.Utils.FrameUtil
import com.tasty.thomas.tastycloudexercice.View.ProductDescriptionView


class MainActivity : AppCompatActivity() {
    var context: Context = this
    lateinit var productGv: GridView
    lateinit var productGvAdapter: ProductGridViewAdapter
    private var jsonKeyTypeProduct = "type"
    private var jsonKeyProduct = "product"
    private var jsonKeyOnList = "onlist"
    private lateinit var jsonProduct: JSONObject


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)







        jsonProduct = loadJSONFromAsset("product.json")

        fillMainMenu()
        fillProductGridView("Entrées")

    }

    fun openProductDescription(idProduct : String, productType : String) {
        var newFragment = ProductDescriptionView.newInstance()
        var args = Bundle()
        args.putString("productType", productType)
        args.putString("idProduct", idProduct)
        newFragment!!.arguments = args
        FrameUtil.changeFrame(R.id.main_framelayout, newFragment, supportFragmentManager.beginTransaction())
//        var selectedFragment: Fragment? = null
//        selectedFragment = ProductDescriptionView.newInstance()
//        var ft: android.support.v4.app.FragmentTransaction? = supportFragmentManager.beginTransaction()
//        ft!!.replace(R.id.main_framelayout, selectedFragment)
//        ft!!.commit()
    }

    fun fillMainMenu() {
        val mainMenu = findViewById(R.id.main_menu) as ListView
        try {
            val products = jsonProduct.getJSONArray(jsonKeyProduct)
            val dishTypes = JsonUtil.jsonArrayToStringListPropertyByKey(products, jsonKeyTypeProduct)
            mainMenu.adapter  =  ItemListViewAdapter(context, dishTypes)
//            val onList = products.getJSONObject(0).getJSONArray("onlist")
//            val productOnList = JsonUtil.jsonArrayToObjectList(onList, Drink::class.java)
//            ItemListViewAdapter(context, productOnList)
        } catch (e: Exception) {
            System.err.println(e)
        }
    }

    fun fillProductGridView(productType: String) {

        try {
            val products = jsonProduct.getJSONArray(jsonKeyProduct)
            val dishs = JsonUtil.findJsonObjectWhereKeyAndPropertyMatch(products, jsonKeyTypeProduct, productType)
            val productType = dishs.optString("type")
            val productOnList = JsonUtil.jsonArrayToObjectList(dishs.getJSONArray(jsonKeyOnList), Product::class.java)

            productGv = findViewById(R.id.main_productGrid)
            productGv.adapter = ProductGridViewAdapter(context, productOnList, productType)
        } catch (e: Exception) {
            System.err.println(e)
        }


    }

    fun loadJSONFromAsset(fileName: String): JSONObject {
        val fileText: String = applicationContext.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        return JSONObject(fileText)
    }
}
