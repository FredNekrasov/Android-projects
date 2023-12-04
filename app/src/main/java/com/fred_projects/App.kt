package com.fred_projects

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()

//Rest API Example
/*
class Example : ComponentActivity() {
    private val repositoryCats = RepositoryCats()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValentinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Cats(repositoryCats, lifecycleScope)
                }
            }
        }
    }
}
@Composable
fun Cats(repositoryCats: RepositoryCats, scope: LifecycleCoroutineScope) {
    Column(Modifier.fillMaxSize()) {
        var type by rememberSaveable { mutableStateOf("") }
        var amount by rememberSaveable { mutableStateOf("") }
        var data by rememberSaveable { mutableStateOf(listOf<FactDTO>()) }
        var status by rememberSaveable { mutableStateOf(Status.NONE) }
        TextField(value = type, onValueChange = {type = it}, label = {Text("enter type")})
        TextField(value = amount, onValueChange = {amount = it}, label = {Text("enter amount")})
        Button(onClick = {
            val amountInt = amount.toIntOrNull() ?: 1
            val sf = repositoryCats.getFacts(type, amountInt)
            scope.launch {
                sf.collect {
                    status = it.first
                    val list = it.second
                    if (list != null) data = list
                }
            }
        }) { Text("get data") }
        Text(
            when(status) {
                Status.WAITING -> "ожидание"
                Status.ERROR -> "ошибка"
                Status.OK -> "хорошо"
                Status.NONE -> ""
            }
        )
        LazyColumn {
            items(data){
                Row {
                    Text(it.text)
                    Text(it.type)
                    Text(it.updateAt?.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))?:"")
                }
            }
        }
    }
}//https://cat-fact.herokuapp.com/facts/random?animal_type=cat
interface CatService {
    @GET("/facts/random/")
//    @Headers("Content-type: text/html")
    fun getFacts(@Query("animal_type") animalType: String,
                 @Query("amount") amount: Int,
//    @Header("Content-type") value: String
//    @Path("id") id: Int
//    @Body fact: Fact
    ): Call<List<Fact>>
    @GET("/facts/random/")
    fun getFact(@Query("animal_type") animalType: String, @Query("amount") amount: Int): Call<Fact>
}
data class Fact(
    val text: String,
    val type: String,
    val updateAt: String?
)
data class FactDTO(
    val text: String,
    val type: String,
    val updateAt: LocalDate?
)
//Репозиторий = хранилище - скрыть данные
//3 репозитория с 1 интерфейсом, если нет данных в room, то надо брать из сайта
interface IRepositoryCats {
    fun getFacts(animalType: String = "cat", amount: Int = 1): StateFlow<Pair<Status, List<FactDTO>?>>
}
class RepositoryCats: IRepositoryCats {
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build()
    private val catService = retrofit.create(CatService::class.java)
    private val scope = CoroutineScope(Dispatchers.IO)
    override fun getFacts(animalType: String, amount: Int): StateFlow<Pair<Status, List<FactDTO>?>> {
        val result1 = catService.getFacts(animalType, amount)
        val result2 = catService.getFact(animalType, amount)
        val sf = MutableStateFlow<Pair<Status, List<FactDTO>?>>(Status.WAITING to null)
        scope.launch {
            try {
                val res = if (amount == 1) result2.execute() else result1.execute()
                if (res.isSuccessful) {
                    val body = res.body()
                    if (body == null) sf.emit(Status.ERROR to null)
                    else {
                        if (amount != 1) sf.emit(Status.OK to (body as List<Fact>).map
                        {
                            FactDTO(it.text, it.type, if (it.updateAt!=null) LocalDate.parse(it.updateAt, DateTimeFormatter.ISO_DATE_TIME) else null)
                        })
                        else sf.emit(Status.OK to (body as Fact).let {
                            listOf(FactDTO(it.text, it.type, if (it.updateAt!=null) LocalDate.parse(it.updateAt, DateTimeFormatter.ISO_DATE_TIME) else null))
                        })
                    }
                } else sf.emit(Status.ERROR to null)
            } catch (e: Exception) {
                sf.emit(Status.ERROR to null)
            }
        }
        return sf
    }
    companion object {
        const val BASE_URL = "https://cat-fact.herokuapp.com"
    }
}
enum class Status {
    WAITING, ERROR, OK, NONE
}*/