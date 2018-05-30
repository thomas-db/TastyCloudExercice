package com.tasty.thomas.tastycloudexercice.View

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
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

class ProductDescriptionView() : Fragment() {
    private lateinit var thisView: View

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var parentContext: Context
        private lateinit var jsonProduct: JSONObject

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
        return thisView
    }


//    companion object {
//        private lateinit var context: Context
//
//        fun newInstance(context: Context) : Fragment {
//            this.context = context
//            return ProductDescriptionView()
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        var rv: View
//        rv = inflater.inflate(R.layout.productdescription_layout, null)
//        val tt = rv.findViewById(R.id.productdescription_text) as TextView
//
//        var titi = arguments!!.get("productType") as String
//        tt.setText(titi)
//
////        val fe = parentFragment as MainActivity
////        fe.fillProductGridView("tit")
////        val parentFrag = this.getParentFragment()
////        parentFrag.fillProductGridView("zef")
//        val mainActivity: MainActivity = context as MainActivity
////        mainActivity.fillProductGridView("zef")
//
//
//
//        System.out.print(titi)
//        titi = arguments!!.get("idProduct") as String
//        System.out.print(titi)
//
//
//
//        val view =  inflater!!.inflate(R.layout.productdescription_layout, container, false)
//        val tata = view.findViewById(R.id.productdescription_text) as TextView
//        tata.setText("gros ça marche")
//        return view
//    }
////    fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
////        return  inflater!!.inflate(R.layout.productdescription_layout, container, false)
////    }
}