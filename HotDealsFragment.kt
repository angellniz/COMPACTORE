package com.example.compactore1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HotDealsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_hot_deals, container, false)

        recyclerView = view.findViewById(R.id.hotDealsRecycler)
        val fab = view.findViewById<FloatingActionButton>(R.id.fabAdd)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        productList.add(Product("Sony WH-CH520", "Wireless headphones", "₹4,499", R.drawable.headphones))
        productList.add(Product("iPhone 15", "A16 chip, 48MP camera", "₹79,900", R.drawable.phone))
        productList.add(Product("Galaxy Watch 6", "AMOLED smartwatch", "₹29,999", R.drawable.watch))
        productList.add(Product("MacBook Air M2", "18-hour battery", "₹1,14,900", R.drawable.laptop))

        adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            Toast.makeText(requireContext(), "Add new deal", Toast.LENGTH_SHORT).show()
        }


        return view
    }
}