package com.hluck.testdemo.ui

import com.hluck.testdemo.ListItem
import com.hluck.testdemo.R
import kotlin.random.Random

fun <T> getRandomElement(list: MutableList<T>): T? {
    if (list.isNotEmpty()) {
        val randomIndex = Random.nextInt(list.size) // 获取随机索引
        return list.removeAt(randomIndex) // 获取并移除该元素
    }
    return null // 如果列表为空，返回 null
}

fun getRandomItem():ListItem{
    return ListItem(
        getRandomElement(titles) ?: "unKnow item",
        getRandomElement(imgs) ?: R.drawable.cover01
    )
}