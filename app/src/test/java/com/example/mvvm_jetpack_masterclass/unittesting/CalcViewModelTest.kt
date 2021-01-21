package com.example.mvvm_jetpack_masterclass.unittesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import com.google.common.truth.Truth.assertThat
import org.junit.Rule

class CalcViewModelTest{
    private lateinit var calcViewModel: CalcViewModel
    private lateinit var calculations: Calculations

    //ensures tests occurs synchronously in case of liveData
    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()

    @Before
    fun setUp() {
        calculations=Mockito.mock(Calculations::class.java)
        Mockito.`when`(calculations.calculateArea(2.1)).thenReturn(13.8474)
        calcViewModel=CalcViewModel(calculations)
    }

    @Test
    fun calculateArea_radiusSent_updateLiveData(){
        calcViewModel.calculateArea(2.1)
        val result=calcViewModel.areaValue.value
        assertThat(result).isEqualTo(13.8473)
    }
}