package com.nurlatif.submission.ui.leagueDetail.team

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nurlatif.submission.R
import com.nurlatif.submission.R.layout.item_team
import com.nurlatif.submission.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team.view.*

class TeamsAdapter(
    private val context: Context,
    private val items: List<Team>,
    private val listener: (Team) -> Unit
) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(item_team, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: Team, listener: (Team) -> Unit) {
            containerView.tv_name.text = item.name
            containerView.tv_location.text = item.location

            Picasso.get().load(item.badge).error(R.drawable.ic_broken)
                .placeholder(R.drawable.loading_animation)
                .into(containerView.img_badge)

            containerView.setOnClickListener { listener(item) }

        }
    }
}