package ar.arstan.tddcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(input: String, result: String, actions: MainActions) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .testTag("input text")
                .fillMaxWidth()
                .padding(8.dp),
            text = input,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier
                .testTag("result text")
                .fillMaxWidth()
                .padding(8.dp),
            text = result,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            MyButton(
                modifier = Modifier.weight(1f),
                text = "1",
                testTag = "number one button",
                onButtonClicked = actions::inputOne
            )
            MyButton(
                modifier = Modifier.weight(1f),
                text = "2",
                testTag = "number two button",
                onButtonClicked = actions::inputTwo
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            MyButton(
                modifier = Modifier.weight(1f),
                text = "+",
                testTag = "plus button",
                onButtonClicked = actions::plus
            )
            MyButton(
                modifier = Modifier.weight(1f),
                text = "=",
                testTag = "equals button",
                onButtonClicked = actions::calculate
            )
        }
    }
}

@Composable
private fun MyButton(
    modifier: Modifier,
    text: String,
    testTag: String,
    onButtonClicked: () -> Unit
) {
    Button(
        modifier = modifier
            .testTag(testTag)
            .padding(4.dp),
        onClick = {
            onButtonClicked()
        }
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(input = "1 + 2", result = "3", object : MainActions {
        override fun inputOne() = Unit
        override fun plus() = Unit
        override fun inputTwo() = Unit
        override fun calculate() = Unit

    })
}