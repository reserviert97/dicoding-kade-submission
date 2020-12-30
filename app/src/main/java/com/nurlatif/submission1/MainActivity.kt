package com.nurlatif.submission1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nurlatif.submission1.R.array.*

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()

    }

    private fun initData(){
        val id = resources.getIntArray(leaguesId)
        val name = resources.getStringArray(leaguesName)
        val desc = resources.getStringArray(leaguesDesc)
        val image = resources.obtainTypedArray(leaguesImage)

        items.clear()
        for (i in id.indices) {
            items.add(Item(id[i], name[i], desc[i], image.getResourceId(i, 0)))
        }

        //Recycle the typed array
        image.recycle()
    }
}