package com.example.cookingtime.`class`

interface StepInterface {

    fun nextStep(): Int {return -1}
    fun canContinue(vararg xs:Boolean): Boolean {return true}
}