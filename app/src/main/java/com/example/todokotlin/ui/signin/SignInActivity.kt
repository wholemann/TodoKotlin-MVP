package com.example.todokotlin.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todokotlin.data.PreferenceHelper
import com.example.todokotlin.R
import com.example.todokotlin.ui.signup.SignUpActivity
import com.example.todokotlin.ui.todolist.MainActivity
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        tv_sign_up_link.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        btn_sign_in.setOnClickListener {
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            startActivity(intent)
            PreferenceHelper.signIn = true
            finish()
        }
    }
}