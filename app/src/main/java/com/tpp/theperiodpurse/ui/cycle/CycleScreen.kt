package com.tpp.theperiodpurse.ui.cycle

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tpp.theperiodpurse.R
import com.tpp.theperiodpurse.Screen
import com.tpp.theperiodpurse.data.*
import com.tpp.theperiodpurse.ui.cycle.components.AverageLengthBox
import com.tpp.theperiodpurse.ui.cycle.components.CurrentCycleBox
import com.tpp.theperiodpurse.ui.cycle.components.CycleHistoryBox
import com.tpp.theperiodpurse.ui.viewmodel.AppViewModel
import kotlin.collections.ArrayList

private var periodLength = (-1).toFloat()
private var cycleLength = (-1).toFloat()

@Composable
fun CycleScreenLayout(
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val dates = ArrayList(appViewModel.getDates())
    val bg = painterResource(R.drawable.colourwatercolour)

    periodLength = calculateAveragePeriodLength(dates)
    cycleLength = calculateAverageCycleLength(dates)

    Box {
        Image(
            painter = bg,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .semantics { contentDescription = "Cycle Page" },
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
                .padding(horizontal = 20.dp, vertical = 25.dp),
        ) {
            CurrentCycleBox(dates = dates)
            Spacer(modifier.height(30.dp))
            Row {
                AverageLengthBox(
                    title = stringResource(R.string.avg_period_len),
                    color = Color(0xFFFEDBDB),
                    length = periodLength,
                    image = painterResource(R.drawable.flow_with_heart),
                )
                Spacer(modifier.width(16.dp))
                AverageLengthBox(
                    title = stringResource(R.string.avg_cycle_len),
                    color = Color(0xFFBAE0D8),
                    length = cycleLength,
                    image = painterResource(R.drawable.menstruation_calendar__1_),
                )
            }
            Spacer(modifier.height(30.dp))
            CycleHistoryBox(
                dates = dates,
                onClickShowFull = {
                    navController.navigate(Screen.CycleFullHistory.name)
                },
            )
            Spacer(modifier.height(80.dp))
        }
    }
}
