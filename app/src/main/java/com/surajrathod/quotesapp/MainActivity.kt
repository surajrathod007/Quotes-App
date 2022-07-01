package com.surajrathod.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    val qText : TextView
    get() = findViewById(R.id.quoteText)

    val qAuthor : TextView
    get() = findViewById(R.id.quoteAuthor)



    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java) //this or application
        setQ(viewModel.getQuote())



    }


    fun setQ(q : Quote){
        qText.text = q.text
        qAuthor.text = q.author
    }

    fun nextQuote(view: View) {
        setQ(viewModel.nextQuote())
    }
    fun prevQuote(view: View) {
        setQ(viewModel.previousQuote())
    }

    fun onShare(view: View) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,viewModel.getQuote().text)

        startActivity(intent)
    }
}