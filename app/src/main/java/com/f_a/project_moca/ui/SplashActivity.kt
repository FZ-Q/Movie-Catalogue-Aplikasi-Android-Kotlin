package com.f_a.project_moca.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.f_a.project_moca.MainActivity
import com.f_a.project_moca.R
import com.f_a.project_moca.UserMainActivity
import com.f_a.project_moca.session.SharedPreferences

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sharedPreference: SharedPreferences = SharedPreferences(this)

        Handler().postDelayed({
            if (sharedPreference.getValueString("role")!=null) {
                val role = sharedPreference.getValueString("role")!!
                if(role == "admin"){
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this, UserMainActivity::class.java))
                    finish()
                }
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 1000)
    }
}
