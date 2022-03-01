package com.example.cookingtime.`class`

class SimpleStep(id: Int, description: String, nextStep: Int) : Step(
    id, description,
) {

    var nextStep: Int = 0;

    init {
        this.id = id
        this.description = description
        this.nextStep = nextStep
    }

    override fun nextStep(): Int {
        return nextStep
    }

    override fun canContinue(vararg xs: Boolean) = true

}