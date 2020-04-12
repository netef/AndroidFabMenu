package com.jackfef.fabmenu

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.cos
import kotlin.math.sin

class FabMenu(val activity: AppCompatActivity, val view: View, val numOfFabs: Int) {
    private val fabSize = 70
    private val r = 100
    fun apply() {
        for (i in 0..numOfFabs) {
            val fab = FloatingActionButton(activity)
            fab.customSize = fabSize
            fab.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            fab.x = view.x + view.measuredWidth / 2 - fabSize / 2
            fab.y = view.y
            val alpha = -180 / numOfFabs * i
            val newX = fab.x + r * cos(Math.toRadians(alpha.toDouble()))
            val newY = fab.y + r * sin(Math.toRadians(alpha.toDouble()))

            fab.animate().x(newX.toFloat()).y(newY.toFloat()).setDuration(1000)




            (activity.window.decorView.rootView as ViewGroup).addView(fab)
        }

    }
}