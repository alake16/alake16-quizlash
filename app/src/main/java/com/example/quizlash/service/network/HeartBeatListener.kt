package com.example.quizlash.service.network

interface HeartBeatListener {
    fun onHeartBeat(heartBeat: HeartBeat)
}