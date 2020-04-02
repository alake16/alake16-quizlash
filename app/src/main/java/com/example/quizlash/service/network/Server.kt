package com.example.quizlash.service.network

interface Server : Runnable {
    fun listenForPackets(port: Int)
    fun addListener(listener: UDPListener)
    fun removeListener(listener: UDPListener)
    fun clearListeners()
}