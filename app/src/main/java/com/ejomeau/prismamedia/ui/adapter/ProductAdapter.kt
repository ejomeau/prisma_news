package com.ejomeau.prismamedia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ejomeau.prismamedia.R
import com.ejomeau.prismamedia.model.Product
import kotlinx.android.synthetic.main.holder_product.view.*
import java.text.SimpleDateFormat


class ProductAdapter(
    private var items: ArrayList<Pair<Product, Boolean>>,
    val productAdapterInterface: ProductAdapterInterface
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductHolder(inflater, parent)
    }

    override fun getItemCount(): Int =
        items.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductHolder).bind(items[position])
    }

    fun refreshView(list: MutableList<Pair<Product, Boolean>>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun removeItem(pair: Pair<Product,Boolean>) {
        val index = items.indexOf(pair)
        if (index != -1)
            items.removeAt(index)
            notifyItemRemoved(index)
    }


    inner class ProductHolder(inflater: LayoutInflater, val parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.holder_product, parent, false)) {

        fun bind(pair: Pair<Product, Boolean>) {
            val product = pair.first
            val state = pair.second
            val format = SimpleDateFormat("MM/dd/yyyy HH:mm")
            itemView.holder_title.text = product.title
            itemView.holder_date.text = format.format(product.datePublication)
            product.image.apply {
                Glide.with(itemView).load(this).placeholder(R.drawable.ic_dashboard_black_24dp)
                    .centerCrop()
                    .into(itemView.holder_image)
            }
            itemView.holder_favorite.isChecked = state
            itemView.holder_favorite.setOnCheckedChangeListener { compoundButton, state ->
                productAdapterInterface.onFavoriteChanged(state, product)
            }
        }
    }
}

interface ProductAdapterInterface {
    fun onFavoriteChanged(state: Boolean, position: Product)
}
