package com.coderus.codingchallenge.catlist

import androidx.lifecycle.LiveData
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataTestUtil {
    /**
     * 获取 LiveData 的值，如果 LiveData 没有更新，则该方法将等待 LiveData 的更新。
     * 这是一个阻塞方法，应该在单元测试中小心使用。
     */
    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)

        // 观察 LiveData，一旦更新，就触发 CountDownLatch 的计数器
        val observer = object : androidx.lifecycle.Observer<T> {

            override fun onChanged(value: T) {
                data[0] = value
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)

        // 等待 LiveData 的更新或超时
        latch.await(2, TimeUnit.SECONDS)

        // 返回 LiveData 的值，或 null（如果值没有更新）
        @Suppress("UNCHECKED_CAST")
        return data[0] as T
    }
}

