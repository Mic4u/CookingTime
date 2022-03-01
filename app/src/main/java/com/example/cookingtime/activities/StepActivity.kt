package com.example.cookingtime.activities

import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.cookingtime.R
import com.example.cookingtime.Utility
import com.example.cookingtime.`class`.*
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_ingriedients.*
import kotlinx.android.synthetic.main.activity_step.*
import kotlin.math.pow

class StepActivity : AppCompatActivity() {


    lateinit var recipe: Recipe
    private var recipeId = 0;

    var next = false


    var started = false
    var finished = false


    var duration = 0.0

    var stepId = 0;

    private val utility: Utility = Utility()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step)

        val bundle = intent.extras
        recipeId = bundle!!.getInt("recipeId")
        stepId = bundle.getInt("step")

        when (recipeId) {
            1 -> recipe = utility.createZapRecipie() //zapiekanka
            2 -> recipe = utility.createKanRecipie() //kanapka z nutellą
            else -> {
                Toast.makeText(this, "Błąd przy przekazywaniu przepisu", Toast.LENGTH_SHORT).show()
            }
        }
        val step: Step = recipe.steps[stepId]

        val button: Button = button3
        val textView: TextView = textView5

        textView.text = step.description


        if (step is TimerStep) {
            button.text = "Start"
            timerView.text = (step.duration * 60).toString()
            duration = step.duration
        } else {
            timerView.text = " "
            if (step is EndStep) {
                button.text = "Zakończ"
            }

        }
    }


    fun onNextClick(view: View) {
        val step: Step = recipe.steps[stepId]

        if (step is TimerStep && !started) {
            started = true;
            startTimeCounter()
        } else {
            if (step.canContinue(started, finished)) {
                var intent = Intent()
                if (step.nextStep() == -1) {
                    intent = Intent(this, MainActivity::class.java)
                } else {
                    intent = Intent(this, StepActivity::class.java)
                    intent.putExtra("recipeId", recipeId)
                    intent.putExtra("step", step.nextStep())
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Nie można przejść do kolejnego kroku", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    private fun startTimeCounter() {

        val txtCounter = timerView

        object : CountDownTimer((duration * 60 * 1000).toLong(), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                if (!next) {

                    txtCounter.text = (millisUntilFinished / 1000).toString()


                } else {
                    cancel()
                }
            }

            override fun onFinish() {

                txtCounter.text = "koniec"
                finished = true
            }
        }.start()
    }
}