package com.c22ps175.playlab.ui.support

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.c22ps175.playlab.R
import com.c22ps175.playlab.data.UserGameLabData
import com.c22ps175.playlab.database.response.MessageSupport
import com.c22ps175.playlab.databinding.ActivitySupportAgentBinding
import com.c22ps175.playlab.ui.dashboard.DashboardActivity
import com.c22ps175.playlab.ui.dashboard.profile.UserProfileActivity
import com.c22ps175.playlab.ui.login.LoginActivity
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SupportAgentActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySupportAgentBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var adapter: FirebaseSupportAgentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupportAgentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.support_agent_activity)

        auth = Firebase.auth
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        db = Firebase.database

        val messagesRef = db.reference.child(MESSAGES_CHILD)

        binding.supprotAgentSendButton.setOnClickListener {
            val friendlyMessage = MessageSupport(
                binding.supportAgentMessageEditText.text.toString(),
                firebaseUser.displayName.toString(),
                firebaseUser.photoUrl.toString(),
                Date().time
            )
            messagesRef.push().setValue(friendlyMessage) { error, _ ->
                if (error != null) {
                    Toast.makeText(this, getString(R.string.support_send_error) + error.message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, getString(R.string.support_send_success), Toast.LENGTH_SHORT).show()
                }
            }
            binding.supportAgentMessageEditText.setText("")
        }

        val manager = LinearLayoutManager(this)
        manager.stackFromEnd = true
        binding.supprotAgentMessageRecyclerView.layoutManager = manager

        val options = FirebaseRecyclerOptions.Builder<MessageSupport>()
            .setQuery(messagesRef, MessageSupport::class.java)
            .build()
        adapter = FirebaseSupportAgentAdapter(options, firebaseUser.displayName)
        binding.supprotAgentMessageRecyclerView.adapter = adapter

    }

    public override fun onResume() {
        super.onResume()
        adapter.startListening()
    }

    public override fun onPause() {
        adapter.stopListening()
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                startActivity(Intent(this@SupportAgentActivity, UserProfileActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val MESSAGES_CHILD = "messages"
        const val EXTRA_USER = "extra_user"
    }
}
