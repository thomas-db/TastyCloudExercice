package com.tasty.thomas.tastycloudexercice.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.tasty.thomas.tastycloudexercice.MainActivity
import com.tasty.thomas.tastycloudexercice.R

class ItemListViewAdapter : BaseAdapter {
    var con: Context
    var items: ArrayList<String>
    private lateinit var inflator: LayoutInflater

    constructor(con: Context, itemList: ArrayList<String>) : super() {
        this.con = con
        this.items = itemList
        inflator = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        var holder: Holder = Holder()
        var rv: View
        rv=inflator.inflate(R.layout.itemmenu_layout, null)
        holder.itemNameTV = rv.findViewById(R.id.itemmenu_name) as TextView
        holder.itemNameTV.setText(items[position].toString())
//        holder.productImg = rv.findViewById(R.id.productlayout_image) as RelativeLayout
//        holder.productImg.setBackgroundResource(R.drawable.img2)
        rv.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val mainActivity: MainActivity = con as MainActivity
                mainActivity.fillProductGridView(holder.itemNameTV.text.toString())
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
        return items.size
    }

    public class Holder{
        lateinit var itemNameTV: TextView
//        lateinit var productImg: RelativeLayout
    }

}