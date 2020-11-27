package com.pepivsky.flipcard

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    lateinit var front_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet

    lateinit var cardFront: CardView
    lateinit var cardBack: CardView
    lateinit var btnFlip: Button

    var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardFront = findViewById(R.id.cardFront)
        cardBack = findViewById(R.id.cardBack)
        btnFlip = findViewById(R.id.btnFlip)

        //1 se crea un direcotrio animator en res
        //2 creamos animator object en nuestros archivos de res, uno para el frente y otro para el reverso
        //3 agregar el animator a los cards
        //modificar el camera sacale

        val scale = applicationContext.resources.displayMetrics.density //obteniendo la escala
        cardFront.cameraDistance = 8000 * scale //asignando distancia de la camara
        cardBack.cameraDistance = 8000 * scale

        //settear los animator
        front_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animator) as AnimatorSet

        //set eventListener
        btnFlip.setOnClickListener {
            if (isFront) {
                front_anim.setTarget(cardFront)
                back_anim.setTarget(cardBack)

                front_anim.start()
                back_anim.start()

                isFront = false
            } else {
                front_anim.setTarget(cardBack)
                back_anim.setTarget(cardFront)

                back_anim.start()
                front_anim.start()

                isFront = true
            }
        }



    }
}