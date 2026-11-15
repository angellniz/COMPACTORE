package com.example.compactore1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        val beauty = view.findViewById<TextView>(R.id.catBeauty)
        val electronics = view.findViewById<TextView>(R.id.catElectronics)
        val kids = view.findViewById<TextView>(R.id.catKids)
        val books = view.findViewById<TextView>(R.id.catBooks)
        val women = view.findViewById<TextView>(R.id.catWomen)
        val men = view.findViewById<TextView>(R.id.catMen)

        beauty.setOnClickListener { openCategory("Beauty") }
        electronics.setOnClickListener { openCategory("Electronics") }
        kids.setOnClickListener { openCategory("Kids") }
        books.setOnClickListener { openCategory("Books") }
        women.setOnClickListener { openCategory("Women's Wear") }
        men.setOnClickListener { openCategory("Men's Wear") }

        return view
    }

    private fun openCategory(category: String) {

        val fragment = CategoryDetailsFragment()

        val bundle = Bundle()
        bundle.putString("category", category)

        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}