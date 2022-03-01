package com.example.cookingtime.`class`

import java.util.ArrayList

data class Recipe(

        var id: Int,
        var title: String,
        var steps: ArrayList<Step>,
        var ingredientsPP: Map<String, Double>,
        var ingredientsScaling: Map<String, (Double, Double) -> Double>
)