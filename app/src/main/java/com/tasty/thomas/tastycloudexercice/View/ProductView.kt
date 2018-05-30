package com.tasty.thomas.tastycloudexercice.View

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import com.tasty.thomas.tastycloudexercice.Adapter.ProductGridViewAdapter
import com.tasty.thomas.tastycloudexercice.MainActivity
import com.tasty.thomas.tastycloudexercice.Model.Product
import com.tasty.thomas.tastycloudexercice.R
import com.tasty.thomas.tastycloudexercice.Utils.JsonUtil
import org.json.JSONObject

class ProductView : Fragment() {
    private lateinit var thisView: View

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var parentContext: Context
        private lateinit var jsonProduct: JSONObject

        fun newInstance(context: Context): Fragment {
            this.parentContext = context
            jsonProduct = JsonUtil.loadJSONFromAsset(MainActivity.jsonFileName, this.parentContext)
            return ProductView()
        }
    }

    fun fillProductList(productType: String) {
        try {
            val products = jsonProduct.getJSONArray(MainActivity.jsonKeyProduct)
            val dishs = JsonUtil.findJsonObjectWhereKeyAndPropertyMatch(products, MainActivity.jsonKeyTypeProduct, productType)
            val productType = dishs.optString("type")
            val productOnList = JsonUtil.jsonArrayToObjectList(dishs.getJSONArray(MainActivity.jsonKeyOnList), Product::class.java)

            val productGV = thisView.findViewById<GridView>(R.id.productList_gridview)
            productGV.adapter = ProductGridViewAdapter(context!!, productOnList, productType)
        } catch (e: Exception) {
            System.err.println(e)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        thisView = inflater!!.inflate(R.layout.productlist_layout, container, false)
        fillProductList(arguments!!.get("productType") as String)
        return thisView
    }
}