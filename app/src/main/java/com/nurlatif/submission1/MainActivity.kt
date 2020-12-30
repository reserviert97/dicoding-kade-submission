package com.nurlatif.submission1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.nurlatif.submission1.R.array.*
import com.nurlatif.submission1.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        initData()

        league_recyclerview.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        league_recyclerview.adapter = RecyclerViewAdapter(this, items){
            val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
            toast.show()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.ITEM_KEY, it)
            startActivity(intent)
        }

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