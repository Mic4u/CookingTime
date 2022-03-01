package com.example.cookingtime.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingtime.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var recipeId = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun onBtnClick(view: View) {
        try {

            val eaters: Float = editTextNumber.text.toString().toFloat()


            if (recipeId == 0) {
                throw Exception("Recipe not chosen")
            } else {

                var intent = Intent(this, MainActivity::class.java)
                intent = Intent(this, IngriedientsActivity::class.java)
                intent.putExtra("recipeId", recipeId)
                intent.putExtra("eaters", eaters)
                startActivity(intent)
            }
        } catch (e: Exception) {

            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickKan(view: View) {
        buttonKan.isEnabled = false
        buttonZap.isEnabled = true
        recipeId = 2
    }

    fun onClickZap(view: View) {
        buttonKan.isEnabled = true
        buttonZap.isEnabled = false
        recipeId = 1
    }
}