package com.c22ps175.playlab.ui.support

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.c22ps175.playlab.R
import com.c22ps175.playlab.databinding.ActivitySupportAgentBinding

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SupportAgentActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySupportAgentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupportAgentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Support Agent"

        binding.supprotAgentSendButton.setOnClickListener {

            val friendlyMessage = binding.supportAgentMessageEditText.text.toString()

        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}