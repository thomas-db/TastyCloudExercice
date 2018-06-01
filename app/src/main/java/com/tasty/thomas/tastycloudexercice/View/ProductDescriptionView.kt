package com.tasty.thomas.tastycloudexercice.View

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.GridView
import android.widget.TextView
import com.tasty.thomas.tastycloudexercice.Adapter.ProductGridViewAdapter
import com.tasty.thomas.tastycloudexercice.MainActivity
import com.tasty.thomas.tastycloudexercice.Model.Product
import com.tasty.thomas.tastycloudexercice.R
import com.tasty.thomas.tastycloudexercice.Utils.FrameUtil
import com.tasty.thomas.tastycloudexercice.Utils.JsonUtil
import org.json.JSONObject
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.DividerItemDecoration
import com.tasty.thomas.tastycloudexercice.Adapter.RecyclerViewHorizontalProductListAdapter
import java.nio.file.Files.size




class ProductDescriptionView() : Fragment() {
    private lateinit var thisView: View

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var parentContext: Context
        private lateinit var jsonProduct: JSONObject
        lateinit var horizontalProducts: ArrayList<Product>

        fun newInstance(context: Context): Fragment {
            this.parentContext = context
            jsonProduct = JsonUtil.loadJSONFromAsset(MainActivity.jsonFileName, this.parentContext)
            return ProductDescriptionView()
        }
    }

    fun fillProductDescription(productId: String, productType: String) {
        try {
            val products = jsonProduct.getJSONArray(MainActivity.jsonKeyProduct)
            val dishs = JsonUtil.findJsonObjectWhereKeyAndPropertyMatch(products, MainActivity.jsonKeyTypeProduct, productType)
            val dishOnList = dishs.optJSONArray(MainActivity.jsonKeyOnList)
            val product = JsonUtil.jsonToObject(JsonUtil.findJsonObjectWhereKeyAndPropertyMatch(dishOnList, "id", productId), Product::class.java)

            if (product != null) {
                thisView.findViewById<TextView>(R.id.productdescription_productName).text = product.name
                thisView.findViewById<TextView>(R.id.productdescription_productAmount).text = product.amount.toString()
                thisView.findViewById<TextView>(R.id.productdescription_productDescription).text = product.description
                thisView.findViewById<TextView>(R.id.productdescription_productPrice).text = product.price.toString()
            }

//            val productGV = thisView.findViewById(R.id.productList_gridview) as GridView
//            productGV.adapter = ProductGridViewAdapter(context!!, productOnList, productType)
        } catch (e: Exception) {
            System.err.println(e)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        thisView = inflater!!.inflate(R.layout.productdescription_layout, container, false)
        fillProductDescription(arguments!!.get("productId") as String, arguments!!.get("productType") as String)
        createHorizontalProductList()
        return thisView
    }

    private fun createHorizontalProductList() {
        val recyclerView = thisView.findViewById(R.id.recyclerview1) as RecyclerView
        val RecyclerViewLayoutManager = LinearLayoutManager(parentContext) //aplicationcontext
        recyclerView.setLayoutManager(RecyclerViewLayoutManager)
        // Adding items to RecyclerView.
        addProductsToRecyclerViewArrayList(arguments!!.get("productType") as String)
        val RecyclerViewHorizontalAdapter = RecyclerViewHorizontalProductListAdapter(horizontalProducts)
        val HorizontalLayout = LinearLayoutManager(parentContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setLayoutManager(HorizontalLayout)
        recyclerView.setAdapter(RecyclerViewHorizontalAdapter)
    }

    fun addProductsToRecyclerViewArrayList(productType: String) {
        val products = jsonProduct.getJSONArray(MainActivity.jsonKeyProduct)
        val dishs = JsonUtil.findJsonObjectWhereKeyAndPropertyMatch(products, MainActivity.jsonKeyTypeProduct, productType)
        horizontalProducts = JsonUtil.jsonArrayToObjectList(dishs.optJSONArray(MainActivity.jsonKeyOnList), Product::class.java)
    }
}