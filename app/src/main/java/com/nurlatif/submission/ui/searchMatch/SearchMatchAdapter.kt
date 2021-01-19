package com.nurlatif.submission.ui.searchMatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.nurlatif.submission.R
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.DetailTeamResponse
import com.nurlatif.submission.network.Event
import com.nurlatif.submission.network.TheSportDBApi
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchMatchAdapter(
    private val context: Context,
    private val items: List<Event>,
    private val api: ApiRepository,
    private val gson: Gson,
    private val listener: (Event) -> Unit
) : RecyclerView.Adapter<SearchMatchAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_match, parent, false),
            api,
            gson
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        override val containerView: View, private val api: ApiRepository,
        private val gson: Gson
    ) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(items: Event, listener: (Event) -> Unit) {
            containerView.homeName.text = items.teamHomeName
            containerView.homeScore.text = items.teamHomeScore

            containerView.awayName.text = items.teamAwayName
            containerView.awayScore.text = items.teamAwayScore

            containerView.matchDate.text = items.eventDate

            containerView.setOnClickListener { listener(items) }


            doAsync {
                val teamAway = gson.fromJson(
                    api.doRequest(TheSportDBApi.getDetailTeam(items.teamAwayId!!)),
                    DetailTeamResponse::class.java
                )

                val teamHome = gson.fromJson(
                    api.doRequest(TheSportDBApi.getDetailTeam(items.teamHomeId!!)),
                    DetailTeamResponse::class.java
                )

                uiThread {
                    teamAway.teams[0].badge.let {  Picasso.get().load(it).error(R.drawable.ic_broken).placeholder(R.drawable.loading_animation).into(containerView.AwayLogo) }
                    teamHome.teams[0].badge.let {  Picasso.get().load(it).error(R.drawable.ic_broken).placeholder(R.drawable.loading_animation).into(containerView.homeTeamLogo) }
                }
            }
        }
    }
}