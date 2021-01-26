package com.nurlatif.submission.ui.searchTeam

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import com.google.gson.Gson
import com.nurlatif.submission.R
import com.nurlatif.submission.R.layout.activity_search_team
import com.nurlatif.submission.model.Team
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.ui.teamDetail.DetailTeamActivity
import kotlinx.android.synthetic.main.activity_search_team.*
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {
    private lateinit var presenter: SearchTeamPresenter
    private lateinit var adapter: SearchTeamAdapter
    private var teams = mutableListOf<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_search_team)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Search Team"

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchTeamPresenter(this, request, gson)

        adapter = SearchTeamAdapter(this, teams) {
            startActivity<DetailTeamActivity>(DetailTeamActivity.ITEM_KEY to it)
        }

        rv_search_team.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.searchMatch)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo((componentName)))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            var debouncePeriod: Long = 500

            private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
            private var searchJob: Job? = null

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery(query, false)
                searchItem.collapseActionView()

                presenter.searchTeam(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchJob?.cancel()
                searchJob = coroutineScope.launch {
                    newText?.let {
                        delay(debouncePeriod)
                        presenter.searchTeam(newText)
                    }
                }
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId != R.id.searchMatch) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun loadData(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        pb_search_team?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_search_team?.visibility = View.GONE
    }
}