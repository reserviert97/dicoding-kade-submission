package com.nurlatif.submission.ui.leagueDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_league.*
import com.nurlatif.submission.R.layout.activity_detail_league
import com.nurlatif.submission.model.League
import com.nurlatif.submission.ui.leaguehighlight.HighlightActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.startActivity

class LeagueDetailActivity : AppCompatActivity(), AnkoLogger {

    companion object {
        const val ITEM_KEY = "league_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail_league)

        val league = intent.extras?.getParcelable<League>(ITEM_KEY)

        debug("[LeagueDetailActivity] Successfully pass data : ${league?.name}")

        detail_title.text = league?.name
        detail_id.text = league?.id.toString()
        detail_desc.text = league?.description

        league?.image.let { Picasso.get().load(it!!).into(detail_image) }

        detail_image.setOnClickListener {
            startActivity<HighlightActivity>(HighlightActivity.ITEM_KEY to league?.id.toString())
        }

        btnDetail.setOnClickListener {
            startActivity<HighlightActivity>(HighlightActivity.ITEM_KEY to league?.id.toString())

        }
    }
}