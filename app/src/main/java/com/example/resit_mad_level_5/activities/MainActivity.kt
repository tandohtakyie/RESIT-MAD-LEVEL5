package com.example.resit_mad_level_5.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resit_mad_level_5.R
import com.example.resit_mad_level_5.activities.AddActivity.Companion.EXTRA_GAME
import com.example.resit_mad_level_5.adapter.GameAdapter
import com.example.resit_mad_level_5.livedata.MainActivityViewModel
import com.example.resit_mad_level_5.model.Game
import kotlinx.android.synthetic.main.activity_main_game.*
import kotlinx.android.synthetic.main.content_main_game.*


const val ADD_GAME_REQUEST_CODE = 100

class MainActivity : AppCompatActivity(){
    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)
    private lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)
        setSupportActionBar(toolbar)
        //gameRepository = GameRepository(this)

        main_button.setOnClickListener {
            startAddActivity()
        }

        initialViews()
        initialViewModel()
    }

    private fun initialViews(){
        rvGames.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter
        createItemTouchHelper().attachToRecyclerView(rvGames)
    }

    private fun initialViewModel(){
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.games.observe(this, Observer { reminders ->
            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(reminders)
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games.removeAt(position)
                viewModel.deleteGame(gameToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun startAddActivity() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, ADD_GAME_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_GAME_REQUEST_CODE -> {
                    val game = data!!.getParcelableExtra<Game>(EXTRA_GAME)

                    viewModel.insertGame(game)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_games -> {
                deleteGames()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteGames() {

        for (i in games.indices) {
            viewModel.deleteGame(games[i])
        }
    }
}
