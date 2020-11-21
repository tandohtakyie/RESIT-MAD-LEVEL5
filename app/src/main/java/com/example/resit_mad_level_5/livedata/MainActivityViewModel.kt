package com.example.resit_mad_level_5.livedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.resit_mad_level_5.model.Game
import com.example.resit_mad_level_5.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val gameRepository = GameRepository(application)

    val games: LiveData<List<Game>> = gameRepository.getAllGames();

    fun insertGame(game: Game){
        ioScope.launch {
            gameRepository.insertGame(game)
        }
    }

    fun deleteGame(game: Game){
        ioScope.launch {
            gameRepository.deleteGame(game)
        }
    }
}
