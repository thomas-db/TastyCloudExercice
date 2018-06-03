package com.tasty.thomas.tastycloudexercice.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.tasty.thomas.tastycloudexercice.MainActivity
import com.tasty.thomas.tastycloudexercice.Model.ItemMenu
import com.tasty.thomas.tastycloudexercice.R
import com.tasty.thomas.tastycloudexercice.View.ProductView

class ItemListViewAdapter : BaseAdapter {
    var con: Context
    var items: ArrayList<ItemMenu>
    private var inflator: LayoutInflater

    constructor(con: Context, itemList: ArrayList<ItemMenu>) : super() {
        this.con = con
        this.items = itemList
        inflator = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: Holder = Holder()
        var rv: View
        rv=inflator.inflate(R.layout.itemmenu_layout, null)
        holder.itemNameTV = rv.findViewById(R.id.itemmenu_name) as TextView
        holder.itemNameTV.setText(items[position].type)
        holder.itemIconIV = rv.findViewById(R.id.itemmenu_icon) as ImageView
        holder.itemIconIV.setImageResource(con.resources.getIdentifier(items[position].icon, "drawable", con.packageName))
        if (items.size == position + 1) {
            holder.itemLineV = rv.findViewById(R.id.itemmenu_bottomline) as View
            holder.itemLineV.visibility = View.INVISIBLE
        }

//        holder.productImg.setBackgroundResource(R.drawable.img2)
        rv.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val mainActivity: MainActivity = con as MainActivity
                (mainActivity.productFragment as ProductView).fillProductList(holder.itemNameTV.text.toString())
                mainActivity.openProductList(holder.itemNameTV.text.toString())
            }

        })
        return rv
    }

    override fun getItem(position: Int): Any? {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    class Holder{
        lateinit var itemNameTV: TextView
        lateinit var itemIconIV: ImageView
        lateinit var itemLineV: View
    }

}