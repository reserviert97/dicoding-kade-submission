package com.nurlatif.submission.ui.leaguehighlight.match

import com.google.gson.Gson
import com.nurlatif.submission.network.*
import com.nurlatif.submission.ui.leaguehighlight.HighlightPresenter
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

class MatchPresenterTest {

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var repository: ApiRepository

    @Mock
    private lateinit var response: Deferred<String>

    @Mock
    private lateinit var view: MatchView

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, repository, gson, TestContextProvider())
    }

    @Test
    fun getLastMatch() {
        val events: MutableList<Event> = mutableListOf()
        val result = EventsResponse(events)
        val leagueId = "33312"

        runBlocking {
            Mockito.`when`(repository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(response)

            Mockito.`when`(response.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventsResponse::class.java
                )
            ).thenReturn(result)

            presenter.getLastMatch(leagueId)

            Mockito.verify(view).loadData(events)
        }


    }

    @Test
    fun getNextMatch() {
        val events: MutableList<Event> = mutableListOf()
        val result = EventsResponse(events)
        val leagueId = "33312"

        runBlocking {
            Mockito.`when`(repository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(response)

            Mockito.`when`(response.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventsResponse::class.java
                )
            ).thenReturn(result)

            presenter.getNextMatch(leagueId)

            Mockito.verify(view).loadData(events)
        }
    }
}