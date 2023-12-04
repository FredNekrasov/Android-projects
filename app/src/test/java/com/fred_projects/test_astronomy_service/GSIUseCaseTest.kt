package com.fred_projects.test_astronomy_service

import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.astronomy.model.StarInfoEntity
import com.fred_projects.education.rest_api.astronomy.model.repository.IStarInfoRepository
import com.fred_projects.education.rest_api.astronomy.model.service.Host
import com.fred_projects.education.rest_api.astronomy.model.service.StarInfo
import com.fred_projects.education.rest_api.astronomy.use_case.GetStarInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration.Companion.seconds

class GSIUseCaseTest {
    private lateinit var fakeRepository: IStarInfoRepository
    private lateinit var useCase: GetStarInfo
    @Before
    fun setUp() {
        fakeRepository = FSIRepository()
        useCase = GetStarInfo(fakeRepository)
    }

    inner class CoroutineTestCase : CoroutineScope {
        var result: StateFlow<Pair<Resource, List<StarInfo>?>> = MutableStateFlow(Pair(Resource.LOADING, null))
        private val job = Job()
        var value = 1
        override val coroutineContext: CoroutineContext
            get() = job + Dispatchers.Unconfined

        fun testSUCCESS(ra: String, dec: String, radius: Float?): Deferred<Unit> {
            val useCase = GetStarInfo(FSIRepository())
            val res = useCase(ra, dec, radius)
            return async {
                result = res
                delay(5.seconds)
            }
        }

        fun testFunction(): Deferred<Unit> {
            return async {
                value = 2
            }
        }
    }

    @Test
    fun testCoroutine() = runBlocking {
        val coroutineTestCase = CoroutineTestCase()
        coroutineTestCase.testFunction().await()
        assertEquals(2, coroutineTestCase.value)
    }

    @Test
    fun testEmptyValues() {
        val useCase = GetStarInfo(FSIRepository())
        val ra = ""
        val dec = ""
        val radius = ""

        val res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)
    }

    @Test
    fun testWhiteSpaceValues() {
        var ra = "       "
        var dec = "      "
        var radius = "   "
        var res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)

        ra = ""
        dec = "      "
        radius = "   "
        res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)

        ra = "       "
        dec = ""
        radius = "   "
        res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)

        ra = "       "
        dec = "      "
        radius = ""
        res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)
    }

    @Test
    fun incorrectValues() {
        var ra = "q"
        var dec = ""
        var radius = ""
        var res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)

        ra = ""
        dec = "q"
        radius = ""
        res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)

        ra = ""
        dec = ""
        radius = "q"
        res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)
        ra = "q"
        dec = "   "
        radius = "   "
        res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)
        ra = "  "
        dec = "q"
        radius = "   "
        res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)
        ra = "   "
        dec = "   "
        radius = "q"
        res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)

        ra = "q"
        dec = "q"
        radius = "q"
        res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.ERROR, res.first)
        assertEquals(null, res.second)
    }

    @Test
    fun correctValues(): Unit = runTest {
        val ra = "q"
        val dec = "q"
        val radius = "0"
        val res = useCase(ra, dec, radius.toFloatOrNull()).value
        assertEquals(Resource.LOADING, res.first)
        assertEquals(null, res.second)

        val coroutineTestCase = CoroutineTestCase()
        coroutineTestCase.testSUCCESS(ra, dec, radius.toFloatOrNull()).await()

        val expectedEntity = StarInfoEntity(dec, listOf(Host("Milky Way")), "qwerty", "49", ra, radius.toFloat()).toStarInfo()
        assertEquals(Resource.SUCCESS, coroutineTestCase.result.value.first)
        assertEquals(listOf(expectedEntity), coroutineTestCase.result.value.second)
    }
}