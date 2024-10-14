package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {

    private val textViewModel: TextViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.textView)


        textViewModel.text.observe(this, Observer { newText ->
            textView.text = newText
        })

        findViewById<View>(R.id.button).setOnClickListener {
            textViewModel.changeText()
        }
    }

}
class TextViewModel : ViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    fun changeText() {
        _text.value = "Button clicked"
    }

}