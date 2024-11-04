package com.hluck.fowdemo

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.reduce

suspend fun main() {
    val flowBuilder = flowOf(1,2,3,4,5)

    flowBuilder.reduce { accumulator, value ->
        println("accumulator: $accumulator, value: $value")
        val result = accumulator + value
//        println(result)
        result
    }


    println()
    flowBuilder.fold(2){ accumulator, value ->
        println("accumulator: $accumulator, value: $value")
        val result = accumulator + value
//        println(result)
        result
    }

    println()

    flowBuilder.collect{
        println(it)
    }

    println()
    val listFlowBuilder = listOf(1,2,3,4,5).asFlow()
    listFlowBuilder.collect{
        println(it)
    }

    println()
    flow<Int> {
        emit(8)
    }.collect{
        println(it)
    }
}