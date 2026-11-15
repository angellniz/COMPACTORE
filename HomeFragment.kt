package com.example.compactore1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val productCard1 = view.findViewById<CardView>(R.id.productCard1)

        productCard1.setOnLongClickListener {

            val popup = PopupMenu(requireContext(), productCard1)
            popup.menuInflater.inflate(R.menu.product_popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->

                when (item.itemId) {

                    R.id.menu_go_site -> {

                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.amazon.in")
                        )

                        startActivity(intent)
                        true
                    }

                    R.id.menu_delete -> {

                        productCard1.visibility = View.GONE
                        true
                    }

                    else -> false
                }

            }

            popup.show()
            true
        }

        val productCard = view.findViewById<CardView>(R.id.productCard2)

        productCard.setOnClickListener {

            val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
            startActivity(intent)

        }

        return view
    }
}