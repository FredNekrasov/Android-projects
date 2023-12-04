package com.fred_projects.test_astronomy_service

import android.content.Context
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.fred_projects.MainActivity
import com.fred_projects.R
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//Open Astronomy Catalog API
@RunWith(AndroidJUnit4::class)
class TestOACAPI {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var appContext: Context

    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }
    @Test
    fun useAppContext() = Assert.assertEquals("com.fred_projects", appContext.packageName)
    @Test
    fun mainTest() {
        composeTestRule.onNodeWithTag(MainActivity.MENU_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.ASTRONOMY_API_BUTTON).performClick()
        emptyValues()

        whiteSpaceValues0()
        clearData()
        whiteSpaceValues1()
        clearData()
        whiteSpaceValues2()
        clearData()
        whiteSpaceValues3()
        clearData()

        incorrectValues0()
        clearData()
        incorrectValues1()
        clearData()
        incorrectValues2()
        clearData()
        incorrectValues3()
        clearData()
        incorrectValues4()
        clearData()

        incorrectValues5()
        correctValues0()
        correctValues1()
    }
    private fun emptyValues() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.error))
    }
    private fun whiteSpaceValues0() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("   ")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.error))
    }
    private fun whiteSpaceValues1() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("     ")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.error))
    }
    private fun whiteSpaceValues2() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("    ")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.error))
    }
    private fun whiteSpaceValues3() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("   ")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("   ")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("  ")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.error))
    }
    private fun incorrectValues0() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("q")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.error))
    }
    private fun incorrectValues1() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("1")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.error))
    }
    private fun incorrectValues2() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("w")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.error))
    }
    private fun incorrectValues3() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("1")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.error))
    }
    private fun incorrectValues4() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("q")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("q")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("q")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.error))
    }
    private fun clearData() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextClearance()
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextClearance()
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextClearance()
    }
    private fun incorrectValues5(): Unit = runTest {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("q")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("q")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("1")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.wait))
        clearData()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.something_wrong))
    }
    private fun correctValues0(): Unit = runTest {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("11:58:30")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("-62:35")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("4.4")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.wait))
        clearData()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.result_is))
        composeTestRule.onNodeWithTag(MainActivity.TEXT_NAME).assertTextEquals("1156-62")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_HOST).assertTextEquals("[Host(value=Milky Way)]")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_REFERENCES).assertTextEquals("[2014BASI...42...47G]")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_RA).assertTextEquals("11:58:30")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_DEC).assertTextEquals("-62:35")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_RADIUS).assertTextEquals("4.4")
    }
    private fun correctValues1() {
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RA).performTextInput("11:58:30")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_DEC).performTextInput("-62:35")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_FIELD_RADIUS).performTextInput("4.4")

        composeTestRule.onNodeWithTag(MainActivity.GET_INFO_BUTTON).performClick()
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.wait))
        composeTestRule.onNodeWithTag(MainActivity.TEXT_NAME).assertTextEquals("1156-62")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_HOST).assertTextEquals("[Host(value=Milky Way)]")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_REFERENCES).assertTextEquals("[2014BASI...42...47G]")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_RA).assertTextEquals("11:58:30")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_DEC).assertTextEquals("-62:35")
        composeTestRule.onNodeWithTag(MainActivity.TEXT_RADIUS).assertTextEquals("4.4")
        composeTestRule.onNodeWithTag(MainActivity.SHOW_RESULT).assertTextEquals(appContext.getString(R.string.result_is))
    }
}