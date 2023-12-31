package com.fred_projects.education

sealed class ScreensRoute(val route: String) {
    data object SolvingTheInequality : ScreensRoute("Solving the inequality")
    data object MainList : ScreensRoute("Main list")
    data object AddEditPage : ScreensRoute("Add edit page")
    data object MathRestAPI : ScreensRoute("Math REST API")
    data object AnimeRestAPI : ScreensRoute("Anime quotes")
    data object AstronomyAPI : ScreensRoute("Astronomy REST API")
}