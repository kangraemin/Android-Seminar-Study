package com.lcw.study.clonebaemin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.timer

class SplashActivity : AppCompatActivity() {
    private var timerTask: Timer? = null      // null을 허용
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        timerTask = timer(period = 1000, initialDelay = 3000) {

            runOnUiThread {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            timerTask?.cancel()

        }


    }
}