package com.app.core_common
import android.util.LruCache

class AppLruCache<K : Any, V : Any>(maxSizeKb: Int = 4 * 1024) {
    private val cache = object : LruCache<K, V>(maxSizeKb) {
        override fun sizeOf(key: K, value: V): Int {
            return 1 // Each entry counts as one unit
        }
    }

    fun put(key: K, value: V) {
        cache.put(key, value)
    }

    fun get(key: K): V? {
        return cache.get(key)
    }

    fun remove(key: K) {
        cache.remove(key)
    }

    fun clear() {
        cache.evictAll()
    }

    fun size(): Int = cache.size()
}
