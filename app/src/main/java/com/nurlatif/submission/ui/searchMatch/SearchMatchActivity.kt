package com.nurlatif.submission.ui.searchMatch

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.nurlatif.submission.R
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.Event
import com.nurlatif.submission.ui.matchDetail.DetailMatchActivity
import kotlinx.android.synthetic.main.activity_search_match.*
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {
    private lateinit var presenter : SearchMatchPresenter
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var adapter : SearchMatchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Search Match"

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, request, gson)

        adapter = SearchMatchAdapter(this, events, request, gson) {
            startActivity<DetailMatchActivity>(
                DetailMatchActivity.ITEM_KEY to it.eventId,
                DetailMatchActivity.ITEM_NAME to it.eventName
            )
        }
        searchMatchRv.adapter = adapter

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

                toast("get $query")

                presenter.searchMatch(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchJob?.cancel()
                searchJob = coroutineScope.launch {
                    newText?.let {
                        delay(debouncePeriod)
                        toast("get $newText")
                        presenter.searchMatch(newText)
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

    override fun loadData(data: List<Event>?) {
        events.clear()
        if (data != null) {
            val finalData = data.filter { value -> value.eventType == "Soccer" }
            events.addAll(finalData)
            adapter.notifyDataSetChanged()
        }

    }


}