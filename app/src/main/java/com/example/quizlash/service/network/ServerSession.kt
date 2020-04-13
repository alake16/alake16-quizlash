package com.example.quizlash.service.network

class ServerSession(var sessionClients: List<Client>, var sessionServer: Server) {
    // send active questions, HB, etc.
    fun sendMessage() {

    }
    // handling responses coming in from clients
    fun onDataReceived() {}
    fun addClient(newClient: Client) {
        // create new active socket connection (set keep alive for max question time)
    }
    fun addServerReplica(newServer: Server) {}
    // will use sendMessage()
    fun emitHB() {}

}