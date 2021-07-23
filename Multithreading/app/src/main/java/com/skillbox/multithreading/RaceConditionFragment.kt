package com.skillbox.multithreading

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.databinding.FragmentRaceConditionBinding
import org.threeten.bp.DateTimeUtils
import org.threeten.bp.Instant

class RaceConditionFragment : Fragment(R.layout.fragment_race_condition) {
    val binding: FragmentRaceConditionBinding by viewBinding()
    private val mainHandler = Handler(Looper.getMainLooper())
    var count: Int = 0

    @Synchronized
    private fun inc(i: Int) {
        count += i
    }

    override

    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // В ходе эксперимента обнаружено, что чем меньше число потоков,
        // тем меньше вероятность того, что числа будут отличаться
        binding.buttonInc.setOnClickListener { button ->
            startIncrementing(button, true)
        }
        binding.buttonSync.setOnClickListener { button ->
            startIncrementing(button, false)
        }
    }

    private fun startIncrementing(button: View, withRaceCondition: Boolean) {
        binding.threadsCount.text.toString().toIntOrNull()?.let { threadsCount ->
            binding.addedNumber.text.toString().toIntOrNull()?.let { addedNumber ->

                binding.textView.text = ""
                button.isEnabled = false
                this.requireActivity().currentFocus?.let { currentFocus ->
                    val inputManager =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(
                        currentFocus.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }

                count = 0
                val expectedNumber = addedNumber * threadsCount
                Thread {
                    val threads = mutableListOf<Thread>()

                    val startTime = DateTimeUtils.toDate(Instant.now()).time
                    val stopTime = DateTimeUtils.toDate(Instant.now()).time

                    for (i in 1..threadsCount) {
                        threads.add(
                            Thread {
                                if (withRaceCondition) {
                                    count += addedNumber
                                } else {
                                    inc(addedNumber)
                                }
                            }.apply { start() }
                        )
                    }

                    threads.forEach {
                        it.join()
                    }

                    mainHandler.post {
                        binding.textView.text =
                            """
                                    Ожидаемое значение: $expectedNumber
                                    Настоящее значение: $count
                                    Затраченное время: ${stopTime - startTime}
                            """.trimIndent()
                        button.isEnabled = true
                        Toast.makeText(
                            requireContext(),
                            "Значение получено",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.start()
            }
        }
    }
}
