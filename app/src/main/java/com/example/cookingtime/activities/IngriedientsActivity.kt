package com.example.cookingtime.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.cookingtime.R
import com.example.cookingtime.Utility
import com.example.cookingtime.`class`.*
import java.time.temporal.TemporalAmount
import java.util.ArrayList
import kotlin.math.pow

class IngriedientsActivity : AppCompatActivity() {

    var reicpeId = 0
    private val utility: Utility = Utility()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingriedients)

        val bundle = intent.extras
        reicpeId = bundle!!.getInt("recipeId")
        val eaters = bundle!!.getFloat("eaters").toDouble()
        var recipe = Recipe(-1, "Puste", arrayListOf<Step>(), emptyMap(), emptyMap())


        when (reicpeId) {
            1 -> recipe = utility.createZapRecipie() //zapiekanka
            2 -> recipe = utility.createKanRecipie() //kanapka z nutellą
            else -> {
                Toast.makeText(this, "Błąd przy przekazywaniu przepisu", Toast.LENGTH_SHORT).show()
            }
        }

        if (recipe.id != -1) {
            val calced: Map<String, Double> =
                calculateIngredients(recipe.ingredientsPP, recipe.ingredientsScaling, eaters)

            var ingriedients: String? = null

            for ((k, v) in calced) {

                if (ingriedients == null) {
                    ingriedients = ("$k: $v\n")
                } else {
                    ingriedients = ingriedients.plus("$k: $v\n")
                }
            }

            val textView: TextView = findViewById(R.id.textView3)
            textView.text = ingriedients
        }
    }


    fun calculateIngredients(
        list: Map<String, Double>,
        scal: Map<String, (Double, Double) -> Double>,
        eaters: Double
    ): Map<String, Double> {

        fun emptyMutil(): MutableMap<String, Double> {
            val empty: Map<String, Double> = emptyMap()
            return empty.toMutableMap()
        }

        var calculated: MutableMap<String, Double> = emptyMutil()
        for ((k, v) in list) {

            val calc: ((Double, Double) -> Double)? = scal[k]

            if (calc != null) {
                calculated[k] = calc(v, eaters)
            }
        }

        return calculated

    }

    fun onStartClick(view: View) {

        var intent = Intent()
        intent = Intent(this, StepActivity::class.java)
        intent.putExtra("recipeId", reicpeId)
        intent.putExtra("step", 0)
        startActivity(intent)
    }


}