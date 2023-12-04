package com.fred_projects.education

sealed class ScreensRoute(val route: String) {
    object SolvingTheInequality : ScreensRoute("Solving the inequality")
    object MainList : ScreensRoute("Main list")
    object AddEditPage : ScreensRoute("Add edit page")
    object MathRestAPI : ScreensRoute("Math REST API")
    object AnimeRestAPI : ScreensRoute("Anime quotes")
    object AstronomyAPI : ScreensRoute("Astronomy REST API")
}