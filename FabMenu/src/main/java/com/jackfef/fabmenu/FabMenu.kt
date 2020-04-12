package com.jackfef.fabmenu

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabMenu(val activity: AppCompatActivity, val view: View, val numOfFabs: Int) {

    fun apply(){
        for (i in 0 until  numOfFabs){
            val fab = FloatingActionButton(activity)
            fab.x = view.pivotX
            fab.y = view.pivotY
            (activity.window.decorView.rootView as ViewGroup).addView(fab)
        }

    }
}