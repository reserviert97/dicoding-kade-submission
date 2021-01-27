package com.nurlatif.submission.ui.leagueDetail.standing

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nurlatif.submission.R.layout.item_standing
import com.nurlatif.submission.network.Standing
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_standing.view.*

class StandingsAdapter(
    private val context: Context,
    private val items: List<Standing>
) : RecyclerView.Adapter<StandingsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(item_standing, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], position + 1)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: Standing, position: Int) {

            containerView.tv_team.text = item.name
            containerView.tv_point.text = item.totalPoint.toString()
            containerView.tv_goal_diff.text = item.goalsdifference.toString()
            containerView.tv_goal_againts.text = item.goalsagainst.toString()
            containerView.tv_goal_for.text = item.goalsFor.toString()
            containerView.tv_loose.text = item.loss.toString()
            containerView.tv_draw.text = item.draw.toString()
            containerView.tv_win.text = item.win.toString()
            containerView.tv_play.text = item.totalPlayed.toString()
            containerView.tv_rank.text = position.toString()

        }
    }
}