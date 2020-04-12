package com.jackfef.fabmenu

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var x = 0f
    var y = 0f
    private lateinit var fab: FloatingActionButton
    private lateinit var fab2: FloatingActionButton
    private lateinit var fab3: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab = FloatingActionButton(this)
        fab2 = FloatingActionButton(this)
        fab3 = FloatingActionButton(this)

        /*fab.layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        )*/


    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        x = ev?.x!!
        y = ev.y
        fab.alpha = 0f
        fab.x = x
        fab.y = y
        fab.animate().x(x - 200).y(y - 100).alpha(1f).setDuration(400)
        fab2.x = x
        fab2.y = y
        fab2.alpha = 0f
        fab2.animate().y(y - 200).alpha(1f).setDuration(400).setStartDelay(100)

        fab3.x = x
        fab3.y = y
        fab3.alpha = 0f
        fab3.animate().x(x + 200).y(y - 100).alpha(1f).setDuration(400).setStartDelay(200)

        if (fab.parent != null)
            (fab.parent as ViewGroup).removeView(fab)
        if (fab2.parent != null)
            (fab2.parent as ViewGroup).removeView(fab2)
        if (fab3.parent != null)
            (fab3.parent as ViewGroup).removeView(fab3)
        main_layout.addView(fab)
        main_layout.addView(fab2)
        main_layout.addView(fab3)
        return super.dispatchTouchEvent(ev)
    }
}