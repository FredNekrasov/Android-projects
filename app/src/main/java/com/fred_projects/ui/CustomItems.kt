package com.fred_projects.ui

import android.content.*
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.fred_projects.MainActivity
import com.fred_projects.R
import com.fred_projects.education.*
import com.fred_projects.education.service_assignment.TestService
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.clipPath


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
    Column(Modifier.fillMaxWidth().height(100.dp), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(stringResource(R.string.drawer_header), color = MaterialTheme.colors.error, fontFamily = FontFamily.Serif, fontSize = 40.sp)
    }
}
@Composable
fun FredDrawerBody(context: Context, navController: NavController) {
    Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
        FredButton({ context.startActivity(Intent(context, TestService::class.java)) }, stringResource(R.string.activity_test_service))
        FredButton({ context.startActivity(Intent(context, Sensors::class.java)) }, stringResource(R.string.activity_second_lw))
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

@Composable
fun FredCard(
    modifier: Modifier,
    mainColor: Color,
    secondaryColor: Color,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp
) {
    Canvas(modifier){
        val clipPath = Path().apply {
            lineTo(size.width - cutCornerSize.toPx(), 0f)
            lineTo(size.width, cutCornerSize.toPx())
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        clipPath(clipPath){
            drawRoundRect(color = mainColor, size = size, cornerRadius = CornerRadius(cornerRadius.toPx()))
            drawRoundRect(color = secondaryColor, topLeft = Offset(size.width - cutCornerSize.toPx(), -100f), size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f), cornerRadius = CornerRadius(cornerRadius.toPx()))
        }
    }
}