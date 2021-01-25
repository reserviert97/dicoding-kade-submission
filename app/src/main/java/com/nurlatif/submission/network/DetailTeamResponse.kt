package com.nurlatif.submission.network

import com.google.gson.annotations.SerializedName

data class DetailTeamResponse(val teams: List<TeamResponse>)

data class TeamResponse(@SerializedName("strTeamBadge") val badge: String)
