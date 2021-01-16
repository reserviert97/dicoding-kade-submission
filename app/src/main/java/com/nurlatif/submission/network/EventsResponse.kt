package com.nurlatif.submission.network

import com.google.gson.annotations.SerializedName

data class EventsResponse(val events: List<Event>)

data class Event(
    @SerializedName("idEvent")
    var eventId: String? = null,
    var eventName: String? = null,
    var date: String? = null
)
