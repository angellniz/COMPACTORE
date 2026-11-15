package com.example.compactore1

import android.app.AlertDialog
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.productImage)
        val name: TextView = view.findViewById(R.id.productName)
        val desc: TextView = view.findViewById(R.id.productDesc)
        val price: TextView = view.findViewById(R.id.productPrice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = productList[position]

        holder.name.text = product.name
        holder.desc.text = product.description
        holder.price.text = product.price
        holder.image.setImageResource(product.image)

        holder.itemView.setOnLongClickListener {

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Product Options")
                .setMessage("Remove this product from Hot Deals?")
                .setPositiveButton("Remove") { _, _ ->
                    productList.removeAt(position)
                    notifyDataSetChanged()
                }
                .setNegativeButton("Cancel", null)
                .show()

            true
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }


}