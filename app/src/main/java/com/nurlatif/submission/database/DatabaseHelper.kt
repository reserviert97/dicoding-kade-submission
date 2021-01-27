package com.nurlatif.submission.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable
import org.jetbrains.anko.db.dropTable

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, 8) {

    companion object {
        private var instance: DatabaseHelper? = null
        const val DB_NAME = "Favorites.db"

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
        createTableFavoriteTeam(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchEntity.TABLE_FAVORITE_MATCH, true)
        db.dropTable(TeamEntity.TABLE_FAVORITE_TEAM, true)
        createTableFavoriteMatch(db)
        createTableFavoriteTeam(db)
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

    private fun createTableFavoriteTeam(db: SQLiteDatabase) {
        db.createTable(
            TeamEntity.TABLE_FAVORITE_TEAM, true,
            TeamEntity.TEAM_ID to TEXT,
            TeamEntity.TEAM_NAME to TEXT,
            TeamEntity.FORMED_YEAR to TEXT,
            TeamEntity.STADIUM to TEXT,
            TeamEntity.LOCATION to TEXT,
            TeamEntity.DESCRIPTION to TEXT,
            TeamEntity.BADGE to TEXT
        )
    }

}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)