package com.naulian.firex

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

object TestManager : FireSync() {

    private var _result = MutableStateFlow("")
    val result = _result.asStateFlow()


    override fun onSync(pathId: String) {
        _result.value = "Syncing $pathId"
    }

    override fun onDetach() {
        _result.value = ""
    }

}

class ExampleUnitTest {

    @Test
    fun syncText() {
        TestManager.detach()
        TestManager.sync("test")

        val expected = "Syncing test"
        val actual = TestManager.result.value
        assertEquals(expected, actual)
    }

    @Test
    fun detachTest() {
        TestManager.sync()
        TestManager.detach()

        val expected = ""
        val actual = TestManager.result.value
        assertEquals(expected, actual)
    }


    @Test
    fun smartSyncTest() {
        TestManager.sync("test")
        TestManager.smartSync("test2")

        val expected = "Syncing test2"
        val actual = TestManager.result.value
        assertEquals(expected, actual)
    }

    @Test
    fun forceSyncTest() {
        TestManager.sync("test")
        TestManager.forceSync("test2")

        val expected = "Syncing test2"
        val actual = TestManager.result.value
        assertEquals(expected, actual)
    }

    @Test
    fun refreshTest(){
        TestManager.sync("test")
        TestManager.refresh()

        val expected = "Syncing test"
        val actual = TestManager.result.value
        assertEquals(expected, actual)
    }
}