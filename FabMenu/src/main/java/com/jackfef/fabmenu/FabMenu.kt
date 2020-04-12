package com.jackfef.fabmenu

import android.animation.Animator
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.cos
import kotlin.math.sin

class FabMenu(val activity: AppCompatActivity, val numOfFabs: Int) : AppCompatActivity() {
    private var duration = 300L
    private var radius = 150
    private var customSize = 70
    private var toggle = true
    private var clickable = true
    val fabs = mutableListOf<FloatingActionButton>()
    private lateinit var view: View

    fun show(view: View) {
        this.view = view
        if (!clickable) return
        clickable = false
        if (toggle) {
            for (i in 0 until numOfFabs) {
                val fab = FloatingActionButton(activity)
                fab.customSize = customSize
                fab.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                fab.x = view.x + view.measuredWidth / 2 - customSize / 2
                fab.y = view.y
                fab.alpha = 0f
                val alpha = i * (-180 / (numOfFabs - 1))
                val newX = fab.x + radius * cos(Math.toRadians(alpha.toDouble()))
                val newY = fab.y + radius * sin(Math.toRadians(alpha.toDouble()))
                fab.animate().x(newX.toFloat())
                    .y(newY.toFloat())
                    .alpha(1f).setDuration(duration)
                    .setStartDelay(i.toLong() * duration / numOfFabs)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {
                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            if (i == numOfFabs - 1)
                                clickable = true
                            fabs.add(fab)
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                        }

                        override fun onAnimationStart(animation: Animator?) {
                        }

                    })
                (activity.window.decorView.rootView as ViewGroup).addView(fab)
            }
        } else
            removeFabs(view)
        toggle = !toggle
    }


    private fun removeFabs(view: View) {
        for (i in 0 until numOfFabs) {
            fabs[i].animate().x(view.x)
                .y(view.y)
                .alpha(0f).setDuration(duration)
                .setStartDelay(i.toLong() * duration / numOfFabs)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        (activity.window.decorView.rootView as ViewGroup).removeView(fabs[i])
                        if (i == numOfFabs - 1) {
                            clickable = true
                            fabs.removeAll(fabs)
                        }
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationStart(animation: Animator?) {
                    }
                })
        }
    }

    fun setRadius(radius: Int): FabMenu {
        if (radius > 300)
            this.radius = 300
        else
            this.radius = radius
        return this
    }

    fun setDuration(duration: Long): FabMenu {
        this.duration = duration
        return this
    }

    fun setCircleSize(size: Int): FabMenu {
        if (size > 150)
            this.customSize = 150
        else
            this.customSize = size
        return this
    }
}