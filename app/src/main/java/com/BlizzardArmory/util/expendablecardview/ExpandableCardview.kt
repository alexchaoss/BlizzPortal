package com.BlizzardArmory.util.expendablecardview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.Transformation
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.view.get
import kotlin.properties.Delegates


class ExpandableCardview(context: Context, attrs: AttributeSet? = null) : CardView(context, attrs),
    View.OnClickListener {

    private var currentState: ExpandableState by Delegates.observable(ExpandableState.CLOSED) { prop, old, new ->
        stateListener?.onChangeStateListener(new)
    }
    private var headerRotationExpanded = 180F
    private var headerRotationCollapsed = 0F
    private var canExpand = true

    var stateListener: StateListener? = null

    init {
        setOnClickListener(this)
    }

    private fun expandCollapseAction() {
        val expandLayout = (this[0] as ViewGroup)[1]
        val headerIndicator = this[1] as ImageView
        currentState = when (currentState) {
            ExpandableState.CLOSED -> {
                animateHeaderIndicatorDown(headerIndicator)
                expand(expandLayout)
                ExpandableState.EXPANDED
            }
            ExpandableState.EXPANDED -> {
                collapse(expandLayout)
                animateHeaderIndicatorUp(headerIndicator)
                ExpandableState.CLOSED
            }
        }
    }

    private fun animateHeaderIndicatorDown(indicator: ImageView) {
        val anim = RotateAnimation(headerRotationCollapsed, headerRotationExpanded, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)

        anim.interpolator = LinearInterpolator()
        anim.repeatCount = 0
        anim.fillAfter = true
        anim.duration = 200

        indicator.startAnimation(anim)
    }

    private fun animateHeaderIndicatorUp(indicator: ImageView) {
        val anim = RotateAnimation(headerRotationExpanded, headerRotationCollapsed, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)

        anim.interpolator = LinearInterpolator()
        anim.repeatCount = 0
        anim.fillAfter = true
        anim.duration = 200

        indicator.startAnimation(anim)
    }

    private fun expand(v: View) {
        val matchParentMeasureSpec: Int = MeasureSpec.makeMeasureSpec((v.parent as View).width, MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec: Int = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight: Int = v.measuredHeight

        v.layoutParams.height = 1
        v.visibility = View.VISIBLE
        val a: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                v.layoutParams.height = if (interpolatedTime == 1f) LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = ((targetHeight / v.context.resources
            .displayMetrics.density) * 1.5).toInt().toLong()
        v.startAnimation(a)
    }

    private fun collapse(v: View) {
        val initialHeight: Int = v.measuredHeight
        val a: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = ((initialHeight / v.context.resources
            .displayMetrics.density) * 1.5).toInt().toLong()
        v.startAnimation(a)
    }

    fun setCanExpand(canExpand: Boolean) {
        this.canExpand = canExpand
    }

    fun isExpanded(): Boolean {
        return currentState == ExpandableState.EXPANDED
    }

    fun registerStateListener(stateListener: StateListener) {
        this.stateListener = stateListener
    }

    override fun onClick(v: View?) {
        if (canExpand) {
            expandCollapseAction()
        }
    }
}

interface StateListener {
    fun onChangeStateListener(newState: ExpandableState)
}