package ar.arstan.tddcalculator

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioUiTest {

    /**
     * A JUnit 4 rule for launching and interacting with a Jetpack Compose UI.
     *
     * This rule, created by `createAndroidComposeRule<MainActivity>()`, serves as the main
     * entry point for testing the UI of the `MainActivity`. It handles the lifecycle of the
     * activity, launching it before each test and tearing it down afterward.
     *
     * The `@get:Rule` annotation is necessary for JUnit 4 to recognize this property as a
     * `TestRule`. In Kotlin, the `@get:` use-site target specifies that the annotation
     * applies to the property's getter.
     *
     * Key functionalities provided by this rule include:
     * 1.  **Lifecycle Management:** Automatically starts and stops the `MainActivity` for each test.
     * 2.  **UI Interaction:** Provides the API to find UI elements (Composables), perform actions
     *     (e.g., clicks, text input), and make assertions about their state (e.g., text content, visibility).
     * 3.  **Synchronization:** Ensures test stability by automatically waiting for the UI to become idle
     *     before executing actions or assertions, preventing flakiness.
     *
     * In essence, `composeTestRule` is the bridge between our test code and the live, running
     * Compose UI of the application.
     */
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val mainPage = MainPage(composeTestRule)

    @Test
    fun sum_of_two_numbers() {
        mainPage.clickNumberOneButton()
        mainPage.assertInputField(expected = "1")

        mainPage.clickOperationPlusButton()
        mainPage.assertInputField(expected = "1+")

        mainPage.clickNumberTwoButton()
        mainPage.assertInputField(expected = "1+2")

        mainPage.clickEqualsButton()
        mainPage.assertInputField(expected = "1+2")
        mainPage.assertResult(expected = "3")
    }

    @Test
    fun sum_of_two_numbers_corner_case() {
        mainPage.clickNumberOneButton()
        mainPage.assertInputField(expected = "1")

        var expected = "1"
        repeat(9) {
            mainPage.clickNumberZeroButton()
            expected += 0
            mainPage.assertInputField(expected = expected)
        }

        mainPage.clickOperationPlusButton()
        mainPage.assertInputField(expected = "1000000000+")

        mainPage.clickNumberTwoButton()
        mainPage.assertInputField(expected = "1000000000+2")

        expected = "1000000000+2"
        repeat(9) {
            mainPage.clickNumberZeroButton()
            expected += 0
            mainPage.assertInputField(expected = expected)
        }

        mainPage.clickEqualsButton()
        mainPage.assertInputField(expected = "1000000000+2000000000")
        mainPage.assertResult(expected = "3000000000")
    }

    @Test
    fun prevent_multiple_zeros() {
        repeat(10) {
            mainPage.clickNumberZeroButton()
            mainPage.assertInputField(expected = "0")
        }

        mainPage.clickOperationPlusButton()
        mainPage.assertInputField(expected = "0+")

        repeat(10) {
            mainPage.clickNumberZeroButton()
            mainPage.assertInputField(expected = "0+0")
        }

        mainPage.clickEqualsButton()
        mainPage.assertInputField(expected = "0+0")
        mainPage.assertResult(expected = "0")

    }

    @Test
    fun prevent_leading_zeros() {
        mainPage.clickNumberZeroButton()
        mainPage.assertInputField(expected = "0")

        mainPage.clickNumberOneButton()
        mainPage.assertInputField(expected = "1")
    }
}
