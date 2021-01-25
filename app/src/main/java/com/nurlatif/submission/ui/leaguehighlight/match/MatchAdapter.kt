package com.nurlatif.submission.ui.leaguehighlight.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.nurlatif.submission.R
import com.nurlatif.submission.model.League
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.DetailTeamResponse
import com.nurlatif.submission.network.Event
import com.nurlatif.submission.network.TheSportDBApi
import com.nurlatif.submission.util.CoroutineContextProvider
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match.view.*
import kotlinx.android.synthetic.main.league_grid_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchAdapter(
    private val context: Context,
    private val items: List<Event>,
    private val api: ApiRepository,
    private val gson: Gson,
    private val listener: (Event) -> Unit
) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {


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
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()
    ) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(items: Event, listener: (Event) -> Unit) {
            containerView.homeName.text = items.teamHomeName
            containerView.homeScore.text = items.teamHomeScore
            containerView.awayName.text = items.teamAwayName
            containerView.awayScore.text = items.teamAwayScore
            containerView.matchDate.text = items.eventDate
            containerView.setOnClickListener { listener(items) }


            GlobalScope.launch(context.main) {
                val teamAway = gson.fromJson(
                    api.doRequest(TheSportDBApi.getDetailTeam(items.teamAwayId!!)).await(),
                    DetailTeamResponse::class.java
                )

                val teamHome = gson.fromJson(
                    api.doRequest(TheSportDBApi.getDetailTeam(items.teamHomeId!!)).await(),
                    DetailTeamResponse::class.java
                )


                teamAway.teams[0].badge.let {
                    Picasso.get().load(it).error(R.drawable.ic_broken)
                        .placeholder(R.drawable.loading_animation).into(containerView.AwayLogo)
                }
                teamHome.teams[0].badge.let {
                    Picasso.get().load(it).error(R.drawable.ic_broken)
                        .placeholder(R.drawable.loading_animation)
                        .into(containerView.homeTeamLogo)
                }

            }
        }
    }
}