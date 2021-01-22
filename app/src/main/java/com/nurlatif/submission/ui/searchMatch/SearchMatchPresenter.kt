package com.nurlatif.submission.ui.searchMatch

import android.util.Log
import com.google.gson.Gson
import com.nurlatif.submission.network.*
import com.nurlatif.submission.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.debug
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

interface SearchMatchView {
    fun loadData(data: List<Event>?)
}

class SearchMatchPresenter(
    private val view: SearchMatchView,
    private val api: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun searchMatch(keyword: String) {

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                api.doRequest(TheSportDBApi.searchEvent(keyword)).await(),
                SearchEventsResponse::class.java
            )

            Log.d("SearchMatchPresenter", "get ${data.event}")
            view.loadData(data.event)

        }

    }
}