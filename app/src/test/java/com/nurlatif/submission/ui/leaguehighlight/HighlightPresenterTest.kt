package com.nurlatif.submission.ui.leaguehighlight

import com.google.gson.Gson
import com.nurlatif.submission.network.ApiRepository
import com.nurlatif.submission.network.DetailLeagueResponse
import com.nurlatif.submission.network.LeagueResponse
import com.nurlatif.submission.utils.TestContextProvider
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HighlightPresenterTest {

    @Mock
    private lateinit var view: HighlightView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var repository: ApiRepository

    @Mock
    private lateinit var response: Deferred<String>

    @Mock
    private lateinit var presenter: HighlightPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = HighlightPresenter(view, repository, gson, TestContextProvider())

    }


    @Test
    fun getLeagueDetail() {
        val leagues: MutableList<LeagueResponse> = mutableListOf(LeagueResponse(leagueId = "33312"))
        val result = DetailLeagueResponse(leagues)
        val leagueId = "33312"

        runBlocking {
            Mockito.`when`(repository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(response)

            Mockito.`when`(response.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    DetailLeagueResponse::class.java
                )
            ).thenReturn(result)

            presenter.getLeagueDetail(leagueId)

            Mockito.verify(view).loadData(leagues[0])
        }
    }

}