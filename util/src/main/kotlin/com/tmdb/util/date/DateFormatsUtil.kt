package com.tmdb.util.date

import java.time.format.DateTimeFormatter

object DateFormatsUtil {
    /**
     * F.e.: 04-12-2001 => "4 Dec 2001"
     */
    val dayShortMonthNameFullYearDateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy")
}
