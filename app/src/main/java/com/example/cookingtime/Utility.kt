package com.example.cookingtime

import com.example.cookingtime.`class`.*
import java.util.ArrayList
import kotlin.math.pow

class Utility {

    fun createZapRecipie(): Recipe {

        val step1= SimpleStep(0,"połóż ser na kromkach chleba",1)
        val step2= TimerStep(1,"włóż kromki chleba do mikrofali na minute" ,2, 0.25)
        val step3= EndStep(2,"Gotowe, smacznego!")
        val zapSteps: ArrayList<Step> = arrayListOf(step1,step2,step3)

        val zapIngs = mapOf("kromka chleba" to 1.0, "plaster sera" to 2.0)
        val zapScal = mapOf("kromka chleba" to { x: Double, y: Double -> x * y }, "plaster sera" to { x: Double, y: Double -> x.pow(y)})
        return Recipe(1, "Zapiekanka", zapSteps, zapIngs, zapScal)
    }

    fun createKanRecipie(): Recipe {


        val step1= SimpleStep(0,"posmaruj kromki nutellą",1)
        val step2= EndStep(1,"Gotowe, smacznego!")
        val kanSteps: ArrayList<Step> = arrayListOf(step1,step2)

        val kanIngs = mapOf("kromka chleba" to 1.0, "łyżeczki nutelli" to 2.0)
        val kanScal = mapOf("kromka chleba" to { x: Double, y: Double -> x * y }, "łyżeczki nutelli" to { x: Double, y: Double -> x * y})
        return Recipe(2, "Kanapka z nutellą", kanSteps, kanIngs, kanScal)
    }

}