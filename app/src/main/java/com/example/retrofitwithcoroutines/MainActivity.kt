package com.example.retrofitwithcoroutines

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.retrofitwithcoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val quoteAPI  = RetrofitHelper.getInstance().create(QuoteApi::class.java)

        GlobalScope.launch {

            val result = quoteAPI.getQuote()
            // Log.d("Result", result.body().toString())
            val quoteListItem = result.body()
           /*
           quoteListItem!!.forEach {
                Log.d("Result", quoteListItem.toString())
            }
            */
            withContext(Dispatchers.Main) {

                quoteListItem?.forEach { quoteItem ->
                    // To each Item present in QuoteList
                    /*
                    val author = quoteItem.author
                    val quote = quoteItem.quote
                    val id = quoteItem.id
                    Log.d("Result", "Author: $author, Quote: $quote, ID: $id")
                    */

                    // To concatenate the quotes together
                    val stringBuilder = StringBuilder()
                    quoteListItem.forEach { quoteItem ->
                        stringBuilder.append(quoteItem.quote).append("  -${quoteItem.author}").append("\n\n") // Append each quote to the StringBuilder
                    }
                    binding.txtQuote.text = stringBuilder.toString()

                }
            }
        }
    }
}