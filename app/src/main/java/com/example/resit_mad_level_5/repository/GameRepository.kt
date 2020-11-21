package com.example.resit_mad_level_5.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.resit_mad_level_5.dao.GameDao
import com.example.resit_mad_level_5.database.GameRoomDatabase
import com.example.resit_mad_level_5.model.Game

class GameRepository(context: Context) {

    private var gameDao: GameDao

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = gameRoomDatabase!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>>{
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game){
        gameDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }

    suspend fun updateGame(game: Game) {
        gameDao.updateGame(game)
    }
}
