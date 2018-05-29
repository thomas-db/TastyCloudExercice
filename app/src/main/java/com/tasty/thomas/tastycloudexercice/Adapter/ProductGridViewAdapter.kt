package com.tasty.thomas.tastycloudexercice.Adapter

import android.content.Context
import android.util.JsonReader
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.Toast
import com.tasty.thomas.tastycloudexercice.MainActivity
import com.tasty.thomas.tastycloudexercice.Model.Product
import com.tasty.thomas.tastycloudexercice.R


class ProductGridViewAdapter : BaseAdapter {
    var con: Context
    var products: ArrayList<Product>
    private lateinit var productType: String
    private lateinit var inflator: LayoutInflater

    constructor(con: Context, products: ArrayList<Product>, productType: String) : super() {
        this.con = con
        this.products = products
        this.productType = productType
        inflator = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        var holder: Holder = Holder()
        var rv: View
        rv=inflator.inflate(R.layout.product_layout, null)
        holder.productNameTv = rv.findViewById(R.id.productlayout_name) as TextView
        holder.productNameTv.setText(products[position].name)
        holder.productImg = rv.findViewById(R.id.productlayout_image) as RelativeLayout
        holder.productImg.setBackgroundResource(R.drawable.img2)
        rv.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val mainActivity: MainActivity = con as MainActivity
                mainActivity.openProductDescription(products[position].id, productType)
            }

        })
        return rv
    }

    override fun getItem(position: Int): Any? {
        return null
        /*return letters[position]*/
    }

    override fun getItemId(position: Int): Long {
        return 0
        /*return position.toLong()*/
    }

    override fun getCount(): Int {
        return products.size
    }

    public class Holder{
        lateinit var productNameTv: TextView
        lateinit var productImg: RelativeLayout
    }

}