import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import com.bm.testing.MainActivity

class CalculatorTests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_all_buttons_are_displayed() {
        composeTestRule.onNodeWithTag("button_0").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button_1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button_2").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button_3").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button_4").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button_5").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button_6").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button_7").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button_8").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button_9").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button_equals").assertIsDisplayed()
        composeTestRule.onNodeWithTag("button_clear").assertIsDisplayed()
    }

    @Test
    fun test_input_display() {
        composeTestRule.onNodeWithTag("button_1").performClick()
        composeTestRule.onNodeWithTag("button_2").performClick()
        composeTestRule.onNodeWithTag("input_text").assertExists()
        composeTestRule.onNodeWithText("12").assertIsDisplayed()
    }

    @Test
    fun test_clear_button() {
        composeTestRule.onNodeWithTag("button_1").performClick()
        composeTestRule.onNodeWithTag("button_clear").performClick()
        composeTestRule.onNodeWithTag("input_text").assertExists()
    }

    @Test
    fun test_evaluate_expression() {
        composeTestRule.onNodeWithTag("button_2").performClick()
        composeTestRule.onNodeWithTag("button_3").performClick()
        composeTestRule.onNodeWithTag("button_+").performClick()
        composeTestRule.onNodeWithTag("button_4").performClick()
        composeTestRule.onNodeWithTag("button_equals").performClick()
        composeTestRule.onNodeWithTag("result_text").assertExists()
        composeTestRule.onNodeWithText("27.0").assertIsDisplayed()
    }
}
