package com.tasty.thomas.tastycloudexercice.Utils

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import com.tasty.thomas.tastycloudexercice.R
import com.tasty.thomas.tastycloudexercice.View.ProductDescriptionView

class FrameUtil {
    companion object {
        /**
         * Change la vue de la frame avec un argument, la clé est frameArg
         */
        fun changeFrameWithStringArg(@IdRes frameId: Int, newFragment: Fragment?, ft: android.support.v4.app.FragmentTransaction?, stringArg: String) {
            var args = Bundle()
            args.putString("frameArg", stringArg)
            newFragment!!.arguments = args
            ft!!.replace(frameId, newFragment)
            ft!!.commit()
        }

        /**
         * Change la vue de la frame
         */
        fun changeFrame(@IdRes frameId: Int, newFragment: Fragment?, ft: android.support.v4.app.FragmentTransaction?) {
            ft!!.replace(frameId, newFragment)
            ft!!.commit()
        }
    }
}