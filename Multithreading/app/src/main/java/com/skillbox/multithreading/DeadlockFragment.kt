package com.skillbox.multithreading

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.databinding.FragmentDeadlockBinding

class DeadlockFragment : Fragment(R.layout.fragment_deadlock) {

    val binding: FragmentDeadlockBinding by viewBinding()

    private var i = 0
    private val lock1 = Any()
    private val lock2 = Any()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDeadlock.setOnClickListener {
            val friend1 = Person("Вася")
            val friend2 = Person("Петя")

            val thread1 = Thread {
                friend1.throwBallToWithDeadlock(friend2)
            }

            val thread2 = Thread {
                friend2.throwBallToWithDeadlock(friend1)
            }

            thread1.start()
            thread2.start()
        }
        binding.buttonWithoutDeadlock.setOnClickListener {
            val friend1 = Person("Вася")
            val friend2 = Person("Петя")

            val thread1 = Thread {
                friend1.throwBallTo(friend2)
            }

            val thread2 = Thread {
                friend2.throwBallTo(friend1)
            }

            thread1.start()
            thread2.start()
        }
    }

    data class Person(
        val name: String
    ) {
        @Synchronized
        fun throwBallToWithDeadlock(friend: Person) {
            Log.d(
                "Person",
                "$name бросает мяч ${friend.name} на потоке ${Thread.currentThread().name}"
            )
            Thread.sleep(500)
            friend.throwBallToWithDeadlock(this)
        }

        fun throwBallTo(friend: Person) {
            synchronized(this) {
                Log.d(
                    "Person",
                    "$name бросает мяч ${friend.name} на потоке ${Thread.currentThread().name}"
                )
                Thread.sleep(500)
            }
            friend.throwBallTo(this)
        }
    }
}
