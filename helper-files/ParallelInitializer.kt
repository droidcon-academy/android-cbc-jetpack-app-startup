package com.droidcon.accelappstartup.startup

import android.content.Context
import androidx.startup.Initializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

class ParallelInitializer<T>(
	private val initializer: Initializer<T>,
	private val resultDelegate: (T) -> Unit
) {

	fun create(context:Context) {
		resultDelegate(initializer.create(context))
	}
}

fun List<ParallelInitializer<out Any>>.parallelInit(context: Context) {
	runBlocking {
		map { initializer ->

			async(Dispatchers.Default) {
				initializer.create(context)
			}
		}.awaitAll()
	}
}
