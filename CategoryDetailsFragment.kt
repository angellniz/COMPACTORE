package com.example.compactore1

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment

class CategoryDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_category_details, container, false)

        val title = view.findViewById<TextView>(R.id.categoryTitle)

        val sub1 = view.findViewById<TextView>(R.id.sub1)
        val sub2 = view.findViewById<TextView>(R.id.sub2)
        val sub3 = view.findViewById<TextView>(R.id.sub3)
        val sub4 = view.findViewById<TextView>(R.id.sub4)

        val category = arguments?.getString("category")

        title.text = category

        when(category){

            "Beauty" -> {
                sub1.text = "Skin Care"
                sub2.text = "Hair Care"
                sub3.text = "Makeup"
                sub4.text = "Fragrance"
            }

            "Electronics" -> {
                sub1.text = "Phones"
                sub2.text = "Laptops"
                sub3.text = "Headphones"
                sub4.text = "Smart Watches"
            }

            "Books" -> {
                sub1.text = "Fiction"
                sub2.text = "Non-Fiction"
                sub3.text = "Academic"
                sub4.text = "Comics"
            }

            "Kids" -> {
                sub1.text = "Toys"
                sub2.text = "Clothes"
                sub3.text = "School Supplies"
                sub4.text = "Games"
            }
        }

        return view
    }
}