package com.nikbrik.generics

fun main() {
//    println(evenNumbersList(listOf(0,1,2,3,4,5,6,7,8,9)))
//    println(evenNumbersList<Float>(listOf<Float>(0.0f,0.1f,2.3f,3.0f,4.0f,4.6f)))
//
//    //Проверка работы с очередью
//    val queue = Queue<Int>()
//    for (i in 1..10) queue.enqueue(i)
//    for (i in 0 until queue.count()+2) println(queue.dequeue()?:"очередь пуста")
//
//    //Этот код должен работать
//    val a:Result<Number, String> = function(true)
//    val b:Result<Any, String> = function(false)
//
//    //Этот код не должен работать
////    val c:Result<Int, CharSequence> = function(true)
////    val d:Result<Int, Any> = function(false)

    val queue = Queue<Int>()
    for (i in 1..10) queue.enqueue(i)
    val queue2 = queue.filter { value -> value%2==1 }
    for (i in 0 until queue2.count()) println(queue2.dequeue()?:"очередь пуста")
    val queue3 = queue.filter ( ::testFilter )
    for (i in 0 until queue3.count()) println(queue3.dequeue()?:"очередь пуста")
}

fun <T:Number> evenNumbersList(numbers:List<T>):List<T>{
    return numbers.filter { it.toDouble()%2==0.0 }
}

fun function(success:Boolean):Result<Int,String>{
    return if(success) Result.Success<Int,String>(1)
    else Result.Error<Int,String>("0")
}

fun testFilter(a: Int):Boolean{
    return a%2==0
}