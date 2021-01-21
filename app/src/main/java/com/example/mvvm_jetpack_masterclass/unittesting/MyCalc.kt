package com.example.mvvm_jetpack_masterclass.unittesting

class MyCalc : Calculations {

    private val pi = 3.14

    override fun calculateCircumference(radius: Double): Double {
        return 2 * pi * radius
    }

    override fun calculateArea(radius: Double): Double {
        return pi * radius * radius
    }
}