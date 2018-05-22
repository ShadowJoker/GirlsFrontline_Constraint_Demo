package com.shadowjoker.constraintdemo

import android.app.Activity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AccelerateDecelerateInterpolator
import kotlinx.android.synthetic.main.activity_main_a.*

class MainActivity : Activity() {

    lateinit var currentSet: ConstraintSet
    lateinit var constraintSetA: ConstraintSet
    lateinit var constraintSetB: ConstraintSet
    lateinit var constraintSetC: ConstraintSet

    lateinit var changeBounds: ChangeBounds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_a)
        initConstrainSet()
        initAnim()
        initAction()
    }

    private fun initConstrainSet() {
        constraintSetA = ConstraintSet()
        constraintSetB = ConstraintSet()
        constraintSetC = ConstraintSet()
        constraintSetA.clone(this.container)
        constraintSetB.clone(this, R.layout.activity_main_b)
        constraintSetC.clone(this, R.layout.activity_main_c)
        currentSet = constraintSetA
    }

    private fun initAnim() {
        changeBounds = ChangeBounds()
        changeBounds.duration = 1000
        changeBounds.interpolator = AccelerateDecelerateInterpolator()
    }

    private fun initAction() {
        this.button_a.setOnClickListener {
            if (currentSet != constraintSetA) doMagicMove(constraintSetA)
        }
        this.button_b.setOnClickListener {
            if (currentSet != constraintSetB) doMagicMove(constraintSetB)
        }
        this.button_c.setOnClickListener {
            if (currentSet != constraintSetC) doMagicMove(constraintSetC)
        }
    }

    private fun doMagicMove(constraintSet: ConstraintSet) {
        currentSet = constraintSet
        TransitionManager.beginDelayedTransition(this.container, changeBounds)
        constraintSet.applyTo(this.container)
    }
}
