package com.fred_projects.education.jumping_rope.model.verification.check_date

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

class CheckJRDate @Inject constructor() : ICheckJRDate {
    override fun check(inf: String): String? {
        return try {
            if (inf.contains(Regex("""^\d{4}.\d{2}.\d{2}$"""))) {
                val date = LocalDate.parse(inf, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                if (date <= LocalDate.now()) inf else null
            } else null
        } catch (e: DateTimeParseException) {
            null
        }
    }
}