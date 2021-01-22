package com.nurlatif.submission.ui.searchMatch

import com.google.gson.Gson
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.Event
import com.nurlatif.submission.network.EventsResponse
import com.nurlatif.submission.network.SearchEventsResponse
import com.nurlatif.submission.ui.matchDetail.DetailMatchPresenter
import com.nurlatif.submission.utils.TestContextProvider
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchMatchPresenterTest {

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var repository: ApiRepository

    @Mock
    private lateinit var response: Deferred<String>

    @Mock
    private lateinit var view: SearchMatchView

    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, repository, gson, TestContextProvider())
    }

    @Test
    fun searchMatch() {
        val events: MutableList<Event> = mutableListOf()
        val result = SearchEventsResponse(events)
        val keyword = "manchester"

        runBlocking {
            Mockito.`when`(repository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(response)

            Mockito.`when`(response.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    SearchEventsResponse::class.java
                )
            ).thenReturn(result)

            presenter.searchMatch(keyword)

            Mockito.verify(view).loadData(events)
        }
    }
}