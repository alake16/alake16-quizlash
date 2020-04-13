package com.example.quizlash.service.network

class ClientSession(var currentServer: Server, val isReplica: Boolean, val backupServers: List<Server>) {
    // receive active questions, HB, etc
    fun onDataReceived() {}
    // send responses, HB, etc.
    suspend fun sendMessage() {}
}