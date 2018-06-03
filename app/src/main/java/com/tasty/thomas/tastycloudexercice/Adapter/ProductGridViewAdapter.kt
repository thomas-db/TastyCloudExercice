package com.tasty.thomas.tastycloudexercice.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.tasty.thomas.tastycloudexercice.MainActivity
import com.tasty.thomas.tastycloudexercice.Model.Product
import com.tasty.thomas.tastycloudexercice.R


class ProductGridViewAdapter : BaseAdapter {
    var con: Context
    var products: ArrayList<Product>
    private var productType: String
    private var inflator: LayoutInflater

    constructor(con: Context, products: ArrayList<Product>, productType: String) : super() {
        this.con = con
        this.products = products
        this.productType = productType
        inflator = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: Holder = Holder()
        var rv: View
        rv = inflator.inflate(R.layout.product_layout, null)
        holder.productNameTv = rv.findViewById(R.id.productlayout_name) as TextView
        holder.productNameTv.setText(products[position].name)
        holder.productImg = rv.findViewById(R.id.productlayout_image) as ImageView
        holder.productImg.setImageResource(con.resources.getIdentifier(products[position].image, "drawable", con.packageName))
        rv.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val mainActivity: MainActivity = con as MainActivity
                mainActivity.openProductDescription(products[position].id, productType)
            }
        })
        return rv
    }

    override fun getItem(position: Int): Any? {
        return products[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return products.size
    }

    class Holder{
        lateinit var productNameTv: TextView
        lateinit var productImg: ImageView
    }

}