package com.example.roompatronesdiseo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var buttonLogin: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide();

        buttonLogin = findViewById<MaterialButton>(R.id.buttonLogin) as MaterialButton
        buttonLogin!!.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when(v!!.id){

            R.id.buttonLogin -> {

                var i: Intent = Intent(this@MainActivity, DashboardActivity::class.java)
                startActivity(i)

            }

        }

    }

}
