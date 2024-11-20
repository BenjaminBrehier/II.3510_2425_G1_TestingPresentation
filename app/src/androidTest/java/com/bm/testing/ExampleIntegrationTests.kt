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
    }

    @Test
    fun test_input_display() {

    }

    @Test
    fun test_clear_button() {

    }

    @Test
    fun test_evaluate_expression() {

    }
}
