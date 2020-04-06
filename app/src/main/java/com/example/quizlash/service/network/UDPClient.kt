package com.example.quizlash.service.network

import java.lang.Exception
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class UDPClient(var networkInfo: NetworkInformation): Client {
    override fun sendMessage(message: String){
        val socket = DatagramSocket()
        println("Message: $message sent to ${networkInfo.type} at port ${networkInfo.port}")
        val data = message.toByteArray(Charsets.UTF_8)
        try {
            val packet = DatagramPacket(data, data.size, InetAddress.getByName(networkInfo.type),
                                        networkInfo.port)
            socket.send(packet)
        } catch (e: Exception) {
            println(e.toString())
            e.printStackTrace()
        }
    }
}