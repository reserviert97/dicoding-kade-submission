package com.nurlatif.submission.ui.leaguehighlight.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nurlatif.submission.R
import com.nurlatif.submission.model.League
import com.nurlatif.submission.network.Event
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match.view.*
import kotlinx.android.synthetic.main.league_grid_item.view.*

class MatchAdapter(
    private val context: Context,
    private val items: List<Event>,
    private val listener: (Event) -> Unit
) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_match, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(items: Event, listener: (Event) -> Unit) {
            containerView.homeName.text = items.teamHomeName
            containerView.homeScore.text = items.teamHomeScore

            containerView.awayName.text = items.teamAwayName
            containerView.awayScore.text = items.teamAwayScore

            containerView.matchDate.text = items.eventDate

//            items.image.let { Picasso.get().load(it).into(containerView.itemImage) }
            containerView.setOnClickListener { listener(items) }
        }
    }
}