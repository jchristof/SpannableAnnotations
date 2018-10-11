package com.example.spannabletest


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannedString
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.message).text = AnnotationSpanner().applyAnnotationSpans(getText(R.string.formatted_currency_span) as SpannedString)
    }
}
