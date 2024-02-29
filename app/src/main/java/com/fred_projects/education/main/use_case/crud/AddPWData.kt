package com.fred_projects.education.main.use_case.crud

import com.fred_projects.education.main.model.repository.IMainRepository
import com.fred_projects.education.main.model.entity.PracticalWork
import com.fred_projects.education.main.model.verification.check_date.ICheckDate
import com.fred_projects.education.main.model.verification.check_image.ICheckImage
import com.fred_projects.education.main.model.verification.check_lvl.ICheckLVL
import com.fred_projects.education.main.model.verification.check_mark.ICheckMark
import com.fred_projects.education.main.model.verification.check_pw.ICheckPW
import com.fred_projects.education.main.model.verification.check_student.ICheckStudent
import com.fred_projects.education.main.model.verification.check_variant.ICheckVariant

class AddPWData(
    private val repository: IMainRepository,
    private val checkPW: ICheckPW,
    private val checkStudent: ICheckStudent,
    private val checkVariant: ICheckVariant,
    private val checkLVL: ICheckLVL,
    private val checkDate: ICheckDate,
    private val checkMark: ICheckMark,
    private val checkImage: ICheckImage
) {
    private var int = 0
    suspend operator fun invoke(id: Int?, pwName: String, student: String, variant: Int?, lvl: Int?, date: String, mark: Int?, image: String): Int {
        int = 0
        if (checkPW.check(pwName) == null) int += 6
        if (checkStudent.check(student) == null) int += 50
        if (checkVariant.check(variant) == null) int += 400
        if (checkLVL.check(lvl) == null) int += 3000
        if (checkDate.check(date) == null) int += 20000
        if (checkMark.check(mark) == null) int += 100000
        if (checkImage.check(image) == null) int += 7000000
        if (int != 0) return int
        val pw = PracticalWork(id, pwName, student, variant!!, lvl!!, date, mark!!, image)
        repository.insertOrUpdateRecord(pw)
        return int
    }
    suspend fun restorePW(pw: PracticalWork) {
        repository.insertOrUpdateRecord(pw)
    }
}