package com.tasty.thomas.tastycloudexercice.View

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import com.tasty.thomas.tastycloudexercice.MainActivity
import com.tasty.thomas.tastycloudexercice.R
import com.tasty.thomas.tastycloudexercice.Utils.FrameUtil
import org.json.JSONObject

class ProductDescriptionView() : Fragment() {

    companion object {
        private lateinit var context: Context

        fun newInstance(context: Context) : Fragment {
            this.context = context
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

//        val fe = parentFragment as MainActivity
//        fe.fillProductGridView("tit")
//        val parentFrag = this.getParentFragment()
//        parentFrag.fillProductGridView("zef")
        val mainActivity: MainActivity = context as MainActivity
//        mainActivity.fillProductGridView("zef")



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