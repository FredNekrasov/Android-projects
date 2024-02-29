package com.fred_projects

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.content.FileProvider
import androidx.navigation.*
import androidx.navigation.compose.*
import com.fred_projects.education.ScreensRoute
import com.fred_projects.education.main.view.*
import com.fred_projects.education.rest_api.anime.view.AQScreen
import com.fred_projects.education.rest_api.astronomy.view.StarInfoScreen
import com.fred_projects.education.rest_api.math.view.MathScreen
import com.fred_projects.education.solving_the_inequality.mvvm.*
import com.fred_projects.ui.message
import com.fred_projects.ui.theme.FredProjectsTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var int = 0
        setContent {
            FredProjectsTheme {
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val viewModel: VM by viewModels()
                    val navController = rememberNavController()
                    var imageLoader by remember { mutableStateOf(false) }
                    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture(), onResult = { imageLoader = it })
                    val launcherPermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission(), onResult = { if (!it) message(getString(R.string.error)) })
                    NavHost(navController, startDestination = ScreensRoute.MainList.route){
                        composable(route = ScreensRoute.MainList.route){
                            MainList(navController, this@MainActivity)
                        }
                        composable(route = ScreensRoute.AddEditPage.route + "?id={id}", arguments = listOf(navArgument("id"){
                            type = NavType.IntType
                            defaultValue = -1
                        })){
                            CUItem(navController, {
                                val file = File(filesDir, "fred$int.jpg")
                                val uri = FileProvider.getUriForFile(this@MainActivity, AUTHORITY, file)
                                val result = this@MainActivity.checkSelfPermission("android.permission.CAMERA")
                                if (result == PackageManager.PERMISSION_GRANTED) {
                                    launcher.launch(uri)
                                } else launcherPermission.launch(CAMERA_PERMISSION)
                                int++
                                return@CUItem uri
                            })
                        }
                        composable(ScreensRoute.SolvingTheInequality.route){
                            EleventhPW(navController, viewModel)
                        }
                        composable(ScreensRoute.MathRestAPI.route){
                            MathScreen(navController)
                        }
                        composable(ScreensRoute.AnimeRestAPI.route){
                            AQScreen(navController)
                        }
                        composable(ScreensRoute.AstronomyAPI.route){
                            StarInfoScreen(navController)
                        }
                    }
                }
            }
        }
    }
    companion object{
        const val CAMERA_PERMISSION = "android.permission.CAMERA"
        const val AUTHORITY = "fred.provider"
        const val NOTIFICATION_CHANNEL_ID = "fred49"
        const val SHOW_RESULT = "Kuroko49"
        const val MENU_BUTTON = "Menu Button"
        const val ASTRONOMY_API_BUTTON = "OACAPI"
        const val TEXT_FIELD_RA = "TF RA"
        const val TEXT_FIELD_DEC = "TF DEC"
        const val TEXT_FIELD_RADIUS = "TF Radius"
        const val TEXT_RA = "T RA"
        const val TEXT_DEC = "T DEC"
        const val TEXT_RADIUS = "T Radius"
        const val TEXT_NAME = "T Name"
        const val TEXT_REFERENCES = "T References"
        const val TEXT_HOST = "T Host"
        const val GET_INFO_BUTTON = "Get Info Button"
    }
}