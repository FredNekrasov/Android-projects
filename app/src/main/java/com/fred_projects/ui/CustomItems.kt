package com.fred_projects.ui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fred_projects.MainActivity
import com.fred_projects.R
import com.fred_projects.education.ScreensRoute
import com.fred_projects.education.SecondLW
import com.fred_projects.education.service_assignment.TestService

@Composable
fun FredButton(click: () -> Unit, inf: String, modifier: Modifier = Modifier){
    Button(click, modifier) { Text(inf) }
}
@Composable
fun FredRadioButton(inf: String, selected: Boolean, onSelect: () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected, onClick = onSelect, colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary, unselectedColor = MaterialTheme.colors.onBackground))
        Spacer(Modifier.width(4.dp))
        Text(text = inf)
    }
}
@Composable
fun FredIconButton(click: () -> Unit, modifier: Modifier = Modifier, image: ImageVector, description: String){
    IconButton(click, modifier) { Icon(image, description) }
}
@Composable
fun FredTextField(value: String, onChangeNumber: (String) -> Unit, id: Int, modifier: Modifier = Modifier) {
    TextField(value, onChangeNumber, modifier, label = { Text(stringResource(id)) }, colors = TextFieldDefaults.outlinedTextFieldColors())
}
@Composable
fun FredFloatingActionButton(onClick: () -> Unit, image: ImageVector, description: String) {
    FloatingActionButton(onClick, backgroundColor = MaterialTheme.colors.primarySurface) { Icon(image, description) }
}
@Composable
fun FredDrawerHeader() {
    Column(
        Modifier
            .fillMaxWidth()
            .height(100.dp), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(stringResource(R.string.drawer_header), color = MaterialTheme.colors.error, fontFamily = FontFamily.Cursive, fontSize = 40.sp)
    }
}
@Composable
fun FredDrawerBody(context: Context, navController: NavController) {
    Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
        FredButton({ context.startActivity(Intent(context, TestService::class.java)) }, stringResource(R.string.activity_test_service))
        FredButton({ context.startActivity(Intent(context, SecondLW::class.java)) }, stringResource(R.string.activity_second_lw))
        FredButton({ navController.navigate(ScreensRoute.SolvingTheInequality.route) }, stringResource(R.string.inequality_task))
        FredButton({ navController.navigate(ScreensRoute.MathRestAPI.route) }, stringResource(R.string.math_service))
        FredButton({ navController.navigate(ScreensRoute.AnimeRestAPI.route) }, stringResource(R.string.anime_quotes))
        FredButton({ navController.navigate(ScreensRoute.AstronomyAPI.route) }, stringResource(R.string.astronomy), Modifier.testTag(MainActivity.ASTRONOMY_API_BUTTON))
    }
}
@Composable
fun FredTopBar(onClick: () -> Unit, onSort: () -> Unit, vector: ImageVector, text: String) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.CRUD), style = MaterialTheme.typography.h6)
        },
        navigationIcon = {
            FredIconButton(onClick, Modifier.testTag(MainActivity.MENU_BUTTON), image = Icons.Default.Menu, description = stringResource(R.string.menu))
        },
        actions = { FredIconButton(onSort, Modifier, vector, text) }
    )
}
fun ComponentActivity.message(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()