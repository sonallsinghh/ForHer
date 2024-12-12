package com.tpp.mindflow.ui.state

import com.tpp.mindflow.data.model.*
import java.time.LocalDate
import kotlin.collections.LinkedHashMap

data class CalendarUIState(
    var days: LinkedHashMap<LocalDate, CalendarDayUIState>,
    var selectedSymptom: Symptom,
)
