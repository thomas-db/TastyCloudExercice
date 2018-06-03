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
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.tasty.thomas.tastycloudexercice.Model.ItemMenu
import com.tasty.thomas.tastycloudexercice.Utils.FrameUtil
import com.tasty.thomas.tastycloudexercice.View.ProductDescriptionView
import com.tasty.thomas.tastycloudexercice.View.ProductView


class MainActivity : AppCompatActivity() {
    private lateinit var jsonProduct: JSONObject
    lateinit var productFragment: Fragment
    lateinit var productDescriptionFragment: Fragment

    lateinit var Number: ArrayList<Product>

    companion object {
        const val jsonKeyTypeProduct = "type"
        const val jsonKeyOnList = "onlist"
        const val jsonKeyProduct = "product"
        const val jsonFileName = "product.json"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         jsonProduct = JsonUtil.loadJSONFromAsset("product.json", this)
         productFragment = ProductView.newInstance(this)
         productDescriptionFragment = ProductDescriptionView.newInstance(this)

        val products = jsonProduct.getJSONArray(jsonKeyProduct)
        if (products.length() > 0) {
            val firstType = (products[0] as JSONObject).optString(jsonKeyTypeProduct)
            openProductList(firstType)
        }

        fillMainMenu()
    }

    fun AddItemsToRecyclerViewArrayList() {
        var fl = 1
        Number = ArrayList()
        Number.add(Product("Kiki", fl.toFloat(), "image", "zef", 12, "5"))
        Number.add(Product("Kiki2", fl.toFloat(), "image", "zef", 12, "5"))
        Number.add(Product("Kiki3", fl.toFloat(), "image", "zef", 12, "5"))
        Number.add(Product("Kiki4", fl.toFloat(), "image", "zef", 12, "5"))


    }

    fun openProductList(productType: String) {
        var args = Bundle()
        args.putString("productType", productType)
        productFragment!!.arguments = args
        FrameUtil.changeFrame(R.id.main_framelayout, productFragment, supportFragmentManager.beginTransaction())
    }


    fun openProductDescription(idProduct: String, productType: String) {
        var args = Bundle()
        args.putString("productType", productType)
        args.putString("productId", idProduct)
        productDescriptionFragment!!.arguments = args
        FrameUtil.changeFrame(R.id.main_framelayout, productDescriptionFragment, supportFragmentManager.beginTransaction())
    }

    fun fillMainMenu() {
        val mainMenu = findViewById(R.id.main_menu) as ListView
        try {
            val products = jsonProduct.getJSONArray(jsonKeyProduct)
            val dishTypes = JsonUtil.jsonArrayToObjectList(products, ItemMenu::class.java)
            mainMenu.adapter = ItemListViewAdapter(this, dishTypes)
//            val onList = products.getJSONObject(0).getJSONArray("onlist")
//            val productOnList = JsonUtil.jsonArrayToObjectList(onList, Drink::class.java)
//            ItemListViewAdapter(context, productOnList)
        } catch (e: Exception) {
            System.err.println(e)
        }
    }
}
