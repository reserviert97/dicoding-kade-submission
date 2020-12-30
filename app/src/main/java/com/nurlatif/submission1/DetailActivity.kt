package com.nurlatif.submission1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import com.nurlatif.submission1.R.layout.activity_detail
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

class DetailActivity : AppCompatActivity(), AnkoLogger {

    companion object {
        val ITEM_KEY = "league_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail)

        val league = intent.extras?.getParcelable<Item>(ITEM_KEY)

        debug("[DetailActivity] Successfully pass data : ${league?.name}")

        detail_title.text = league?.name
        detail_id.text = league?.id.toString()
        detail_desc.text = league?.description

        league?.image.let { Picasso.get().load(it!!).into(detail_image) }
    }
}