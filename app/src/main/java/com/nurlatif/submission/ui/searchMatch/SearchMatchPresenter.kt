package com.nurlatif.submission.ui.searchMatch

import android.util.Log
import com.google.gson.Gson
import com.nurlatif.submission.network.*
import org.jetbrains.anko.debug
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

interface SearchMatchView {
    fun loadData(data: List<Event>?)
}

class SearchMatchPresenter(private val view: SearchMatchView, private val api: ApiRepository, private val gson: Gson) {


    fun searchMatch(keyword: String) {

        doAsync {
            val data = gson.fromJson(
                api.doRequest(TheSportDBApi.searchEvent(keyword)),
                SearchEventsResponse::class.java
            )

            Log.d("SearchMatchPresenter", "api ${TheSportDBApi.searchEvent(keyword)}")
            Log.d("SearchMatchPresenter", "get ${data.event}")

            uiThread {
                view.loadData(data.event)
            }

        }

    }
}