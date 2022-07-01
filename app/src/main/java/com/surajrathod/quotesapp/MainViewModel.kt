package com.surajrathod.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context : Context) : ViewModel() {

    lateinit var quoteList : Array<Quote>
    var index = 0

    init {
        quoteList = loadQuote()
    }

    private fun loadQuote(): Array<Quote> {

        val inputstream = context.assets.open("quotes.json")        //read file
        val size : Int = inputstream.available()        //get the file size
        val buffer = ByteArray(size)                    //create byte array of that size
        inputstream.read(buffer)                        //store data in bytearray
        inputstream.close()
        val json = String(buffer,Charsets.UTF_8)            //parse
        val gson = Gson()

        return gson.fromJson(json,Array<Quote>::class.java)

    }


    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun previousQuote() = quoteList[--index]

}