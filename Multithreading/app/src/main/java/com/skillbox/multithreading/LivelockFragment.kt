package com.skillbox.multithreading

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.databinding.FragmentLivelockBinding
import timber.log.Timber

class LivelockFragment : Fragment(R.layout.fragment_livelock) {
    val philosophers = listOf(Philosopher(), Philosopher())
    val wandPool = mutableListOf(Wand(), Wand())
    val binding: FragmentLivelockBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLivelock.setOnClickListener {
            philosophers.forEach {
                Thread {
                    while (true) {
                        var result = true
                        if (it.wands.size < 2)
                            synchronized(wandPool) {
                                result = it.tryToTakeWandFrom(wandPool)
                            }
                        else
                            it.eat(wandPool)
                        Thread.sleep(1000)
                        if (!result)
                            synchronized(wandPool) {
                                it.putAllWands(wandPool)
                            }
                        Thread.sleep(1000)
                    }
                }.start()
            }
        }
        binding.buttonWithoutLivelock.setOnClickListener {
            val semaphore = Semaphore()
            philosophers.forEach {
                Thread {
                    while (true) {
                        var mustPutAllWands = false
                        if (it.wands.size < 2) {
                            if (semaphore.requestPermission(it, wandPool))
                                synchronized(wandPool) {
                                    mustPutAllWands = it.tryToTakeWandFrom(wandPool).not()
                                }
                        } else {
                            it.eat(wandPool)
                            mustPutAllWands = true
                        }
                        Thread.sleep(1000)
                        if (mustPutAllWands)
                            synchronized(wandPool) {
                                it.putAllWands(wandPool)
                            }
                        Thread.sleep(1000)
                    }
                }.start()
            }
        }
    }

    class Philosopher() {
        val wands = mutableListOf<Wand>()
        fun tryToTakeWandFrom(wandPool: MutableList<Wand>): Boolean {
            val wand = wandPool.removeLastOrNull()
            return if (wand == null) {
                Timber.d("$this - ?????????????????? ?????????????? ???? ????????????????")
                false
            } else {
                wands.add(wand)
                Timber.d("$this - ???????? ?????????????? ${wands.size}")
                true
            }
        }

        fun putAllWands(wandPool: MutableList<Wand>) {
            wandPool.addAll(wands)
            wands.clear()
            Timber.d("$this - ???????????? ?????? ???????? ??????????????")
        }

        fun eat(wandPool: MutableList<Wand>) {
            Timber.d("$this - ?????????? ??????")
        }
    }

    class Wand()

    class Semaphore() {
        fun requestPermission(philosopher: Philosopher, wandPool: MutableList<Wand>): Boolean {
            return !(wandPool.size == 1 && philosopher.wands.size == 0)
        }
    }
}
