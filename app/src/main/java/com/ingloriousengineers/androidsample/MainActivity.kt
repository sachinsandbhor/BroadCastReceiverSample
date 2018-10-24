package com.ingloriousengineers.androidsample

import android.content.Intent
import android.os.Bundle
import android.widget.TextView


class MainActivity : BaseActivity() {

    lateinit var helloWorld: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helloWorld = findViewById(R.id.hello) as TextView
        helloWorld.setOnClickListener { startActivity(Intent(this, Main2Activity::class.java)) }
    }
}
