package com.example.mvvm_jetpack_masterclass.unittesting


import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class MyCalcTest{
    private lateinit var myCalc: MyCalc

    @Before
    fun setUp() {
        myCalc= MyCalc()
    }

    @Test
    fun calculateCircumference_radiusGiven_returnsCorrectResult(){
        val result=myCalc.calculateCircumference(2.1)
        assertThat(result).isEqualTo(12.3)
    }
    @Test
    fun calculateArea_areaGiven_returnCorrectResult(){
        val result=myCalc.calculateArea(2.1)
        assertThat(result).isEqualTo(21.1)
    }
}