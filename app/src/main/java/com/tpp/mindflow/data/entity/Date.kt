package com.tpp.mindflow.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tpp.mindflow.data.helper.DaysConverter
import com.tpp.mindflow.data.helper.DurationConverter
import com.tpp.mindflow.data.model.CrampSeverity
import com.tpp.mindflow.data.model.Exercise
import com.tpp.mindflow.data.model.FlowSeverity
import com.tpp.mindflow.data.model.Mood
import java.time.Duration
import java.util.Date

@Entity(tableName = "dates")
data class Date(
    @PrimaryKey
    @TypeConverters(DaysConverter::class)
    val date: Date,
    val flow: FlowSeverity?,
    val mood: Mood?,
    @TypeConverters(DurationConverter::class)
    val exerciseLength: Duration?,
    val exerciseType: Exercise?,
    val crampSeverity: CrampSeverity?,
    @TypeConverters(DurationConverter::class)
    val sleep: Duration?,
    val notes: String,
)
