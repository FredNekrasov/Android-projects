package com.fred_projects.education.main.model.verification.check_date

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

class CheckDate @Inject constructor() : ICheckDate {
    override fun check(inf: String?): String? {
        return try {
            if ((inf != null) && (inf.contains(Regex("""^\d\d\.\d\d\.\d\d\d\d$""")))) {
                val date = LocalDate.parse(inf, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                if ((date < LocalDate.now()) && (date > LocalDate.of(2023, 9, 1))) {
                    inf
                } else null
            } else null
        } catch (e: DateTimeParseException) {
            null
        }
    }
}