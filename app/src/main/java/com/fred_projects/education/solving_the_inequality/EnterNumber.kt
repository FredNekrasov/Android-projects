package com.fred_projects.education.solving_the_inequality

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.fred_projects.R


@Composable
fun EnterNumber(value: String, onChangeNumber: (String) -> Unit, modifier: Modifier, id: Int){
    TextField(
        value,
        onValueChange = {
            if ((it.toFloatOrNull() != null) || (it == "") || (it == "-")) onChangeNumber(it)
        },
        modifier,
        label = { Text(stringResource(id)) },
        colors = TextFieldDefaults.outlinedTextFieldColors()
    )
}
@Composable
fun NinthPW(modifier: Modifier = Modifier) {
    Text("${stringResource(R.string.first_number)}x + ${stringResource(R.string.second_number)} < 0", modifier.testTag(Calculations.FORMULA))
    var firstN by rememberSaveable { mutableStateOf("") }
    EnterNumber(firstN, { firstN = it }, modifier.testTag(Calculations.FIRST_NUMBER), R.string.first_number)
    var secondN by rememberSaveable { mutableStateOf("") }
    EnterNumber(secondN, { secondN = it }, modifier.testTag(Calculations.SECOND_NUMBER), R.string.second_number)
    val calculations = Calculations()
    Text(calculations.checkData(firstN.toFloatOrNull(), secondN.toFloatOrNull(), stringResource(R.string.error), stringResource(R.string.inequality)), modifier.testTag(Calculations.RESULT), textAlign = TextAlign.Center)
}