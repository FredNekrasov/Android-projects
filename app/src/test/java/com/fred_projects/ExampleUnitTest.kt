package com.fred_projects

import com.fred_projects.education.solving_the_inequality.mvvm.Model
import com.fred_projects.education.solving_the_inequality.mvvm.Solution
import org.junit.Assert.assertEquals
import org.junit.Test

/*Example local unit test, which will execute on the development machine (host).
 *See [testing documentation](http://d.android.com/tools/testing).*/
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun checkModel() {
        val model = Model()
        var firstN: Float? = null
        var secondN: Float? = null

        var res = model.checkData(firstN, secondN)
        assertEquals(res.first, Solution.ERROR)
        assertEquals(res.second, null)
        assertEquals(res.first.getSolution(res.second), "1")

        firstN = 0f
        secondN = 0f
        res = model.checkData(firstN, secondN)
        assertEquals(res.first, Solution.INFORMATION)
        assertEquals(res.second, null)
        assertEquals(res.first.getSolution(res.second), "2")

        firstN = 0f
        secondN = 9f
        res = model.checkData(firstN, secondN)
        assertEquals(res.first, Solution.INFORMATION)
        assertEquals(res.second, null)
        assertEquals(res.first.getSolution(res.second), "2")

        firstN = 9f
        secondN = 9f
        res = model.checkData(firstN, secondN)
        assertEquals(res.first, Solution.THIRD_SOLUTION)
        assertEquals(res.second, -1.0f)
        assertEquals(res.first.getSolution(res.second), "x < ${-secondN / firstN}\nx = (− ∞ , ${-secondN/firstN})")

        firstN = 0f
        secondN = -9f
        res = model.checkData(firstN, secondN)
        assertEquals(res.first, Solution.FIRST_SOLUTION)
        assertEquals(res.second, -9f)
        assertEquals(res.first.getSolution(res.second), "$secondN < 0\nx = (− ∞ , + ∞)")

        firstN = -9f
        secondN = -9f
        res = model.checkData(firstN, secondN)
        assertEquals(res.first, Solution.SECOND_SOLUTION)
        assertEquals(res.second, -1.0f)
        assertEquals(res.first.getSolution(res.second), "x > ${-secondN / firstN}\nx = (− ∞ , ${-secondN / firstN})")
    }
}