package com.younis.e_bakerstask.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import com.younis.e_bakerstask.R
import com.younis.e_bakerstask.model.Product
import com.younis.e_bakerstask.ui.CartActivity

class CartRecyclerAdapter(
    private val products: List<Product>,
    private val clickListner: ClickListner
) : RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var product_image: ImageView
        var product_name: TextView
        var total_product_price: TextView
        var product_subname: TextView
        var prduct_price_per_one: TextView
        var item_increment: MaterialButton
        var item_decrement: MaterialButton
        var item_quantity: TextView


        init {
            product_image = itemView.findViewById(R.id.cart_product_image)
            product_name = itemView.findViewById(R.id.product_subname)
            product_subname = itemView.findViewById(R.id.product_name)
            item_increment = itemView.findViewById(R.id.cart_add_sign)
            item_decrement = itemView.findViewById(R.id.cart_sub_sign)
            item_quantity = itemView.findViewById(R.id.cart_current_qty)
            prduct_price_per_one = itemView.findViewById(R.id.price_per_one)
            total_product_price = itemView.findViewById(R.id.product_price_total)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.product_name.setText(products[position].name)
        holder.product_subname.setText(products[position].name)
        holder.total_product_price.setText(products[position].ProductInCartTotal.toString())
        holder.prduct_price_per_one.setText(products[position].sale_price.toString() + "L.E")
        holder.item_quantity.setText(products[position].ProductInCartQty.toString())
        Picasso.get().load(products[position].image).into(holder.product_image)

        holder.item_increment.setOnClickListener {
            var current_qty = holder.item_quantity.text.toString()
            var current_qty_double = current_qty.toDouble() + .5
            var singel_item_price: Double = products[position].sale_price.toDouble()
            var total_amount = singel_item_price * current_qty_double
            holder.total_product_price.setText(total_amount.toString())
            clickListner.onClicked(current_qty_double, products[position].id, total_amount)
            holder.item_quantity.setText(current_qty_double.toString())

        }

        holder.item_decrement.setOnClickListener {
            var current_qty = holder.item_quantity.text.toString()
            var current_qty_double = current_qty.toDouble() - .5

            var singel_item_price: Double = products[position].sale_price.toDouble()
            var total_amount = singel_item_price * current_qty_double
            holder.total_product_price.setText(total_amount.toString())
            clickListner.onClicked(current_qty_double, products[position].id, total_amount)
            holder.item_quantity.setText(current_qty_double.toString())
        }
    }

    override fun getItemCount() = products.size

    interface ClickListner {
        fun onClicked(qty: Double, product_ID: Int, total_amount: Double)
    }


}