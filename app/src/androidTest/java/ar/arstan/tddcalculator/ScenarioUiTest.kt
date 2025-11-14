package ar.arstan.tddcalculator

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioUiTest {

    /**
     *
     * The `@get:Rule` annotation is from JUnit 4 and marks the `composeTestRule`
     *  property as a JUnit `TestRule`. A `TestRule` allows you to add behavior
     *  that runs before and after each test method. In Kotlin, since
     *  `composeTestRule` is a property, we must specify that the annotation
     *   applies to the property's getter using the `@get:` use-site target. This
     *   is how JUnit discovers and applies the rule.
     *
     * The `composeTestRule` is a test rule for Jetpack Compose UI tests.
     * It is created using `createAndroidComposeRule<MainActivity>()`, which launches
     * the `MainActivity` of the app.
     *
     * We need this rule for a few key reasons:
     * 1.  **Lifecycle Management:** It handles the lifecycle of the `MainActivity`,
     *     ensuring it's launched and torn down correctly for each test.
     * 2.  **UI Interaction:** It provides the primary API for interacting with the
     *     Compose UI. We can use it to find UI elements (Composables), perform
     *     actions on them (like clicks or text input), and assert their state
     *     (e.g., visibility, text content).
     * 3.  **Synchronization:** The rule automatically waits for the UI to be idle
     *     before performing actions or assertions, which helps prevent flaky tests.
     *
     * In essence, `composeTestRule` is the bridge between our test code and the
     * live, running Compose UI of our application.
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
}
