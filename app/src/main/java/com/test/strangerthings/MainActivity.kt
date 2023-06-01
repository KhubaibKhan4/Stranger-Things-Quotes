package com.test.strangerthings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.strangerthings.adapter.MyAdapter
import com.test.strangerthings.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private val adapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()

        val repository = Repository()
        val mainViewModelProvider = MainViewModelFactory(repository)

        mainViewModel =
            ViewModelProvider(this, mainViewModelProvider)[MainViewModel::class.java]

        mainViewModel.getQuotes()
        mainViewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { adapter.setData(it) }
                Log.d("Response", response.body().toString())
            }else{
                Log.d("Response",response.errorBody().toString())
            }

        })

    }

    private fun setUpRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}