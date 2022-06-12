package com.c22ps175.playlab.ui.support

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.c22ps175.playlab.R
import com.c22ps175.playlab.database.response.MessageSupport
import com.c22ps175.playlab.databinding.ItemMessageChatbotBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class FirebaseSupportAgentAdapter(
    options: FirebaseRecyclerOptions<MessageSupport>,
    private val currentUserName: String?
) : FirebaseRecyclerAdapter<MessageSupport, FirebaseSupportAgentAdapter.MessageViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_message_chatbot, parent, false)
        val binding = ItemMessageChatbotBinding.bind(view)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int, model: MessageSupport) {
        holder.bind(model)
    }

    inner class MessageViewHolder(private val binding: ItemMessageChatbotBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MessageSupport) {
            binding.chatBotTextMessage.text = item.text
            setTextColor(item.name, binding.chatBotTextMessage)
            binding.chatBotTextMessenger.text = item.name
            Glide.with(itemView.context)
                .load(item.photoUrl)
                .circleCrop()
                .into(binding.chatBotAvatarMessenger)
            if (item.timestamp != null) {
                binding.chatBotTextTimestamp.text = DateUtils.getRelativeTimeSpanString(item.timestamp)
            }
        }

        private fun setTextColor(userName: String?, textView: TextView) {
            if (currentUserName == userName && userName != null) {
                textView.setBackgroundResource(R.drawable.rounded_message_blue)
            } else {
                textView.setBackgroundResource(R.drawable.rounded_message_yellow)
            }
        }
    }
}