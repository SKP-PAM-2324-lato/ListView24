package com.example.listview

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val items = mutableListOf("Jabłko", "Banan", "Wiśnia", "Gruszka")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener{_, _, position, _ ->
            val selectedItem = items[position]
            Toast.makeText(this, "Wybrano owoc: $selectedItem", Toast.LENGTH_SHORT).show()
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val text = findViewById<EditText>(R.id.editText).text.toString()
            items.add(text)
            adapter.notifyDataSetChanged()
        }

        listView.setOnItemLongClickListener{ _, _, position, _ ->
            items.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }


    }
}