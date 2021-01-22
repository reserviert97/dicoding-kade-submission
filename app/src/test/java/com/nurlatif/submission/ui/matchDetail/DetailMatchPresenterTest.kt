package com.nurlatif.submission.ui.matchDetail

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

class DetailMatchPresenterTest {

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var repository: ApiRepository

    @Mock
    private lateinit var response: Deferred<String>

    @Mock
    private lateinit var view: DetailMatchView

    @Mock
    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, repository, gson, TestContextProvider())
    }

    @Test
    fun getMatchDetail() {
        val matches: MutableList<MatchResponse> = mutableListOf(MatchResponse(matchId = "1212", awayId = "1", homeId = "2"))
        val result = DetailMatchResponse(matches)
        val matchId = "1212"

        runBlocking {
            Mockito.`when`(repository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(response)

            Mockito.`when`(response.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    DetailMatchResponse::class.java
                )
            ).thenReturn(result)

            presenter.getMatchDetail(matchId)

            Mockito.verify(view).loadData(matches[0])
        }
    }

    @Test
    fun getTeamBadgeUrl() {
        val teams: MutableList<TeamResponse> = mutableListOf(TeamResponse(badge = "link to url"))
        val result = DetailTeamResponse(teams)
        val teamHomeId = "1212"
        val teamAwayId = "2121"

        runBlocking {
            Mockito.`when`(repository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(response)

            Mockito.`when`(response.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    DetailTeamResponse::class.java
                )
            ).thenReturn(result)

            presenter.getTeamBadgeUrl(teamHomeId, teamAwayId)

            Mockito.verify(view).loadImage(teams[0].badge, teams[0].badge)
        }
    }
}