package com.nurlatif.submission.network

import com.google.gson.annotations.SerializedName

data class EventsResponse(val events: List<Event>)

data class SearchEventsResponse(val event: List<Event>)

data class Event(
    @SerializedName("idEvent")
    var eventId: String? = null,
    @SerializedName("strEvent")
    var eventName: String? = null,
    @SerializedName("strSport")
    var eventType: String? = null,
    @SerializedName("dateEvent")
    var eventDate: String? = null,
    @SerializedName("idHomeTeam")
    var teamHomeId: String? = null,
    @SerializedName("strHomeTeam")
    var teamHomeName: String? = null,
    @SerializedName("intHomeScore")
    var teamHomeScore: String? = null,
    @SerializedName("idAwayTeam")
    var teamAwayId: String? = null,
    @SerializedName("strAwayTeam")
    var teamAwayName: String? = null,
    @SerializedName("intAwayScore")
    var teamAwayScore: String? = null
)
