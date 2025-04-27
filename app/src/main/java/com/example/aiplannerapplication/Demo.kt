package com.example.aiplannerapplication

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job1 = launch {
            delay(1000)
            print("inside job1")
        }
        val job2 = async {
            delay(100)
            print("inside job2")
            "Result"
        }
        val res2 = job2.await()
        print(res2)
        job1.join()
    }
}