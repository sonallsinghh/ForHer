package com.tpp.mindflow.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kizitonwose.calendar.core.CalendarDay
import com.tpp.mindflow.data.model.FlowSeverity
import com.tpp.mindflow.data.model.Symptom
import com.tpp.mindflow.ui.calendar.components.Day
import com.tpp.mindflow.ui.state.CalendarDayUIState
import com.tpp.mindflow.ui.viewmodel.AppViewModel

// Creates the days
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDay(
    day: CalendarDay,
    calendarDayUIState: CalendarDayUIState?,
    activeSymptom: Symptom,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel
) {
    val (dayColor, iconId) = getDayColorAndIcon(activeSymptom, calendarDayUIState, appViewModel)
    val isPredicted = calendarDayUIState?.flow == FlowSeverity.Predicted
    Day(day.date, dayColor, iconId, onClick, modifier, isPredicted)
}
