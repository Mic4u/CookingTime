package com.example.cookingtime.`class`

class TimerStep(id: Int, description: String, nextStep: Int, duration: Double) : Step(id, description,
) {

    var nextStep: Int=0;
    var duration: Double=0.0;

    init {
        this.id = id
        this.description = description
        this.nextStep = nextStep
        this.duration = duration
    }

    override fun nextStep(): Int {return nextStep}

    override fun canContinue(vararg xs:Boolean)=xs[0]&&xs[1]

}