package com.nurlatif.submission.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable
import org.jetbrains.anko.db.dropTable

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 4) {

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        createTableFavoriteMatch(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchEntity.TABLE_FAVORITE_MATCH, true)
        createTableFavoriteMatch(db)
    }

    private fun createTableFavoriteMatch(db: SQLiteDatabase) {
        db.createTable(
            MatchEntity.TABLE_FAVORITE_MATCH, true,
            MatchEntity.MATCH_ID to TEXT,
            MatchEntity.DATE to TEXT,
            MatchEntity.TEAM_HOME to TEXT,
            MatchEntity.TEAM_HOME_ID to TEXT,
            MatchEntity.TEAM_HOME_SCORE to TEXT,
            MatchEntity.AWAY_TEAM to TEXT,
            MatchEntity.AWAY_TEAM_ID to TEXT,
            MatchEntity.AWAY_TEAM_SCORE to TEXT,
            MatchEntity.MATCH_TYPE to TEXT
        )
    }

}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)