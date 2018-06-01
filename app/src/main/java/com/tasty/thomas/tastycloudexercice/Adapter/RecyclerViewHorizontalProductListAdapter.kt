package com.tasty.thomas.tastycloudexercice.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tasty.thomas.tastycloudexercice.R
import java.nio.file.Files.size
import android.widget.Toast
import com.tasty.thomas.tastycloudexercice.Model.Product
import java.nio.file.Files.size


class RecyclerViewHorizontalProductListAdapter(private val list: List<Product>) : RecyclerView.Adapter<RecyclerViewHorizontalProductListAdapter.MyView>() {

    inner class MyView(view: View) : RecyclerView.ViewHolder(view) {

        var productNameTV: TextView
        var productPriceTV: TextView

        init {
            productNameTV = view.findViewById(R.id.horizontalproduct_productName) as TextView
            productPriceTV = view.findViewById(R.id.horizontalproduct_productPrice) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.horizontalproduct_layout, parent, false)

        return MyView(itemView)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.productNameTV.text = list[position].name
        holder.productPriceTV.text = list[position].price.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}