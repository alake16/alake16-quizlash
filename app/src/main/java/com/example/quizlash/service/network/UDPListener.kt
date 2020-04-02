package com.example.quizlash.service.network

interface UDPListener {
    fun onUDP(data: String)
}