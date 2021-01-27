package com.nurlatif.submission.ui.leagues

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import com.nurlatif.submission.R.layout.item_league
import com.nurlatif.submission.model.League
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_league.view.*

class LeaguesAdapter(
    private val context: Context,
    private val items: List<League>,
    private val listener: (League) -> Unit
) : RecyclerView.Adapter<LeaguesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(item_league, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(items: League, listener: (League) -> Unit) {
            containerView.itemTitle.text = items.name
            items.image.let { Picasso.get().load(it).into(containerView.itemImage) }
            containerView.setOnClickListener { listener(items) }
        }
    }
}