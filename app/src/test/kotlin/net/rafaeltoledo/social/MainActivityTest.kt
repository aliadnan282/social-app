package net.rafaeltoledo.social

import net.rafaeltoledo.social.ui.feature.main.MainActivity
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Test
    fun checkIfActivityIsSuccessfullyCreated() {
        assertNotNull(Robolectric.setupActivity(MainActivity::class.java))
    }
}