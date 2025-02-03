package com.example.anoji

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.anoji.adapter.MessageAdapter
import com.example.anoji.model.ChatMessage

class ChatActivity : AppCompatActivity() {

    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: ImageButton
    private lateinit var messageAdapter: MessageAdapter
    private val messageList = mutableListOf<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        messageRecyclerView = findViewById(R.id.messageRecyclerView)
        messageEditText = findViewById(R.id.messageEditText)
        sendButton = findViewById(R.id.sendButton)

        // Initialize RecyclerView and Adapter
        messageAdapter = MessageAdapter(messageList)
        messageRecyclerView.adapter = messageAdapter
        messageRecyclerView.layoutManager = LinearLayoutManager(this)

        sendButton.setOnClickListener {
            sendMessage()
        }

    }

    private fun sendMessage() {
        val messageText = messageEditText.text.toString().trim()
        if (messageText.isNotEmpty()) {
            // Add message to the list
            addMessage(messageText)
            // Clear EditText
            messageEditText.text.clear()
            // Scroll to the last item
            messageRecyclerView.scrollToPosition(messageList.size - 1)

        } else {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addMessage(messageText :String){
        val newMessage = ChatMessage(messageText, true) // Assuming true means it's a user message
        messageList.add(newMessage)
        messageAdapter.notifyItemInserted(messageList.size - 1)
        // Dummy bot response - you should replace this with your actual logic
        addBotReply("Ok, " + messageText)
    }

    private fun addBotReply(botText : String){
        val newMessage = ChatMessage(botText, false)
        messageList.add(newMessage)
        messageAdapter.notifyItemInserted(messageList.size - 1)
    }




}