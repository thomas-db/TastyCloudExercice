package com.tasty.thomas.tastycloudexercice.View

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tasty.thomas.tastycloudexercice.MainActivity
import com.tasty.thomas.tastycloudexercice.Model.Product
import com.tasty.thomas.tastycloudexercice.R
import com.tasty.thomas.tastycloudexercice.Utils.JsonUtil
import org.json.JSONObject
import android.support.v7.widget.LinearLayoutManager
import com.tasty.thomas.tastycloudexercice.Adapter.RecyclerViewHorizontalProductListAdapter
import android.view.MotionEvent
import android.widget.Toast
import android.view.GestureDetector
import android.widget.ImageView


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
                thisView.findViewById<ImageView>(R.id.productdescription_productImage).setImageResource(parentContext.resources.getIdentifier(product.image, "drawable", parentContext.packageName))
            }
        } catch (e: Exception) {
            System.err.println(e)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        thisView = inflater!!.inflate(R.layout.productdescription_layout, container, false)
        fillProductDescription(arguments!!.get("productId") as String, arguments!!.get("productType") as String)
        createHorizontalProductList(arguments!!.get("productType") as String)
        return thisView
    }

    private fun createHorizontalProductList(productType: String) {
        var recyclerView = thisView.findViewById(R.id.recyclerview1) as RecyclerView
        var RecyclerViewLayoutManager = LinearLayoutManager(parentContext)
        recyclerView.setLayoutManager(RecyclerViewLayoutManager)
        addProductsToRecyclerViewArrayList(arguments!!.get("productType") as String)
        var RecyclerViewHorizontalAdapter = RecyclerViewHorizontalProductListAdapter(horizontalProducts, parentContext)
        var HorizontalLayout = LinearLayoutManager(parentContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = HorizontalLayout
        recyclerView.adapter = RecyclerViewHorizontalAdapter

        // Adding on item click listener to RecyclerView.
        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            internal var gestureDetector = GestureDetector(parentContext, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(motionEvent: MotionEvent): Boolean {
                    return true
                }
            })

            override fun onInterceptTouchEvent(Recyclerview: RecyclerView, motionEvent: MotionEvent): Boolean {
                val childView = Recyclerview.findChildViewUnder(motionEvent.x, motionEvent.y)
                if (childView != null && gestureDetector.onTouchEvent(motionEvent)) {
                    val RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(childView)
                    fillProductDescription(horizontalProducts[RecyclerViewItemPosition].id, productType)
                    createHorizontalProductList(productType)
                }
                return false
            }

            override fun onTouchEvent(Recyclerview: RecyclerView, motionEvent: MotionEvent) {
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })
    }

    fun addProductsToRecyclerViewArrayList(productType: String) {
        val products = jsonProduct.getJSONArray(MainActivity.jsonKeyProduct)
        val dishs = JsonUtil.findJsonObjectWhereKeyAndPropertyMatch(products, MainActivity.jsonKeyTypeProduct, productType)
        horizontalProducts = JsonUtil.jsonArrayToObjectList(dishs.optJSONArray(MainActivity.jsonKeyOnList), Product::class.java)
    }
}