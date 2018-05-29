package com.tasty.thomas.tastycloudexercice.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tasty.thomas.tastycloudexercice.R

class ProductDescriptionView : Fragment() {
    companion object {
        fun newInstance() : Fragment {
            return ProductDescriptionView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rv: View
        rv = inflater.inflate(R.layout.productdescription_layout, null)
        val tt = rv.findViewById(R.id.productdescription_text) as TextView

        var titi = arguments!!.get("productType") as String
        tt.setText(titi)
        System.out.print(titi)
        titi = arguments!!.get("idProduct") as String
        System.out.print(titi)
        val view =  inflater!!.inflate(R.layout.productdescription_layout, container, false)
        val tata = view.findViewById(R.id.productdescription_text) as TextView
        tata.setText("gros ça marche")
        return view
    }
//    fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return  inflater!!.inflate(R.layout.productdescription_layout, container, false)
//    }
}