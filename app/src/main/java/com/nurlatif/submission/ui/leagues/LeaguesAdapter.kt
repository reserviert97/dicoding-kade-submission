package com.nurlatif.submission.ui.leagues

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import com.nurlatif.submission.R.layout.league_grid_item
import com.nurlatif.submission.model.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.league_grid_item.view.*

class LeaguesAdapter(
    private val context: Context,
    private val items: List<Item>,
    private val listener: (Item) -> Unit
) : RecyclerView.Adapter<LeaguesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(league_grid_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(items: Item, listener: (Item) -> Unit) {
            containerView.itemTitle.text = items.name
            items.image.let { Picasso.get().load(it).into(containerView.itemImage) }
            containerView.setOnClickListener { listener(items) }
        }
    }
}