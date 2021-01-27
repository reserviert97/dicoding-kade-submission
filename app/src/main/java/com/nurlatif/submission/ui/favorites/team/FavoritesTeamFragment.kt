package com.nurlatif.submission.ui.favorites.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nurlatif.submission.R.layout.fragment_favorites_team
import com.nurlatif.submission.model.Team
import com.nurlatif.submission.ui.teamDetail.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_favorites_team.*
import org.jetbrains.anko.support.v4.startActivity

class FavoritesTeamFragment : Fragment(), FavoritesTeamView {
    private var teams = mutableListOf<Team>()
    private lateinit var presenter: FavoritesTeamPresenter
    private lateinit var adapter: FavoritesTeamAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = FavoritesTeamPresenter(this, requireContext())

        adapter = FavoritesTeamAdapter(requireContext(), teams){
            startActivity<DetailTeamActivity>(DetailTeamActivity.ITEM_KEY to it)
        }

        rv_favorite_teams.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_favorites_team, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoritesTeam()
    }

    override fun loadData(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }



}