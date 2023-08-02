package com.droidcon.accelappstartup.tools

object PerfTracer {
    fun measureMs(work: () -> Unit): Long {
        val start = System.currentTimeMillis()
        work()
        return System.currentTimeMillis() - start
    }
}