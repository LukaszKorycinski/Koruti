package com.example.koruti

import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*


val TAG = "tag"


fun exampleBlocking() {
    Log.e(TAG, "one")
    runBlocking {
        //blokuje wątek
        printDelayed("two")
    }
    Log.e(TAG, "three")
}


fun exampleBlockingDispatcher() {
    runBlocking(Dispatchers.Default) {
        Log.e(TAG, "its thread ${Thread.currentThread().name}")
        printDelayed("its thread ${Thread.currentThread().name}")
    }
    Log.e(TAG, "its therad ${Thread.currentThread().name}")
}

fun exampleLaunchGloabal() = runBlocking {
    Log.e(TAG, "one its therad ${Thread.currentThread().name}")
    GlobalScope.launch {
        Log.e(TAG, "two its thread ${Thread.currentThread().name}")
        printDelayed("its thread ${Thread.currentThread().name}")
    }
    Log.e(TAG, "three its therad ${Thread.currentThread().name}")
    delay(2000)
}


fun exampleLaunchGloabalWaiting() = runBlocking {
    Log.e(TAG, "one its therad ${Thread.currentThread().name}")
    val job = GlobalScope.launch {
        Log.e(TAG, "two its thread ${Thread.currentThread().name}")
        printDelayed("its thread ${Thread.currentThread().name}")
    }
    Log.e(TAG, "three its therad ${Thread.currentThread().name}")
    job.join()//wait aż coruti się skończy
}

fun exampleLaunchCoroutineScopeAndroidUI(textView: TextView) = runBlocking {
    Log.e(TAG, "one its therad ${Thread.currentThread().name}")
    val job = this.launch(Dispatchers.Main) {
        textView.text = "tutaj można"
        Log.e(TAG, "two its thread ${Thread.currentThread().name}")
        printDelayed("three its thread ${Thread.currentThread().name}")
    }
    Log.e(TAG, "four its therad ${Thread.currentThread().name}")
    job.join()//wait aż coruti się skończy
}

fun exampleLaunchCoroutineScope() = runBlocking {
    Log.e(TAG, "one its therad ${Thread.currentThread().name}")
    val job = this.launch(Dispatchers.Default) {
        Log.e(TAG, "two its thread ${Thread.currentThread().name}")
        printDelayed("three its thread ${Thread.currentThread().name}")
    }
    Log.e(TAG, "four its therad ${Thread.currentThread().name}")
    job.join()//wait aż coruti się skończy
}


fun exampleAsyncAwait() = runBlocking {//to całe się wykona jednocześnie
    val deferred1 = async { calculatHardThings(10) }
    val deferred2 = async { calculatHardThings(10) }
    val deferred3 = async { calculatHardThings(10) }

    val sum = deferred1.await() + deferred2.await() + deferred3.await()

    Log.e(TAG, "async/await reult = $sum" )
}



fun exampleWithContext() = runBlocking {//to się wykona po kolei
    val result1 = withContext(Dispatchers.Default) { calculatHardThings(10) }
    val result2 = withContext(Dispatchers.Default) { calculatHardThings(10) }
    val result3 = withContext(Dispatchers.Default) { calculatHardThings(10) }

    val sum = result1 + result2 + result3

    Log.e(TAG, "async/await reult = $sum" )
}



suspend fun calculatHardThings(startNumber: Int): Int {
    delay(1000)
    return startNumber * 10
}

suspend fun printDelayed(message: String) {
    delay(1000)
    Log.e(TAG, message)
}

fun String.toStringKtoryZwracaIntaXD():Int{
return 123
}

