package com.nikbrik.generics

class Queue<T> {
    private val list:MutableList<T> = mutableListOf()
    fun enqueue(item: T){list.add(item)}
    fun dequeue(): T?{
        return list.removeFirstOrNull()
    }
    fun count() = list.count()
    fun filter (isFiltered:(value:T)->Boolean):Queue<T>{
        val newQueue = Queue<T>()
        list.forEach(){if (isFiltered(it)) newQueue.enqueue(it)}
        return newQueue
    }
}