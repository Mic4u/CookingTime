package com.example.cookingtime.`class`

class EndStep(id: Int, description: String) : Step(id, description) {


    init {
        this.id = id
        this.description = description
    }

    override fun nextStep(): Int {
        return -1
    }

    override fun canContinue(vararg xs: Boolean) = true

}