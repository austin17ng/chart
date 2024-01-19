package com.vnpay.testapplication.expandable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vnpay.testapplication.R
import com.vnpay.testapplication.databinding.ActivityExpanableBinding

class ExpanableActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExpanableBinding // Binding object for the activity_main.xml layout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpanableBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Assuming recyclerView is your RecyclerView
        val itemsList = listOf(
            Item("Title1", "Description 1-0", "Description 1-1", "Description 1-2"),
            Item("Title2", "Description 2-0", "Description 2-1", "Description 2-2"),
            Item("Title3", "Description 1-0", "Description 1-1", "Description 1-2"),
            Item("Title4", "Description 2-0", "Description 2-1", "Description 2-2"),
            Item("Title5", "Description 1-0", "Description 1-1", "Description 1-2"),
            Item("Title6", "Description 2-0", "Description 2-1", "Description 2-2"),
            Item("Title7", "Description 1-0", "Description 1-1", "Description 1-2"),
            Item("Title8", "Description 2-0", "Description 2-1", "Description 2-2"),
            Item("Title9", "Description 1-0", "Description 1-1", "Description 1-2"),
            Item("Title10", "Description 2-0", "Description 2-1", "Description 2-2"),
            Item("Title11", "Description 1-0", "Description 1-1", "Description 1-2"),
            Item("Title12", "Description 2-0", "Description 2-1", "Description 2-2"),
            Item("Title13", "Description 1-0", "Description 1-1", "Description 1-2"),
            Item("Title14", "Description 2-0", "Description 2-1", "Description 2-2"),
            Item("Title15", "Description 2-0", "Description 2-1", "Description 2-2"),
        )

        val adapter = ExpandableAdapter(itemsList)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)

    }
}