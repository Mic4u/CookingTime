package com.example.cookingtime.`class`


abstract class Step(id: Int, description: String) : StepInterface {

    var id: Int = 0
    var description: String = ""


    init {
        this.id = id
        this.description = description

    }


}