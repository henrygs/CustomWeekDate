package com.example.customdateweek

import android.content.Context
import android.graphics.Color.red
import android.os.Build
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class CustomItemInfoWeek(context: Context, val attributeSet: AttributeSet?) :
    HorizontalScrollView(context, attributeSet) {

    constructor(context: Context) : this(context, null)

    private val linearBase = createLinearLayout()
    private val diceList = arrayListOf<Int>()
    var clickCallback: (item: String) -> Unit = { it }

    private fun CalendarDays(): Array<String?>{

        var date = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        val formatter = DateTimeFormatter.ofPattern("dd")

        val days = arrayOfNulls<String>(7)

        for (i in 0 until 7) {
            days[i] = formatter.format(date)
            date = date.plusDays(1)
        }
        return days
    }

    private fun daysOfWeek(): Int {
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)

        diceList.addAll(
            arrayListOf(
                Calendar.SUNDAY,
                Calendar.MONDAY,
                Calendar.TUESDAY,
                Calendar.WEDNESDAY,
                Calendar.THURSDAY,
                Calendar.FRIDAY,
                Calendar.SATURDAY
            )
        )
        return day
    }

    fun renderComponent() {
        this.addView(linearBase)

        val day: Int = daysOfWeek()

        for (i in 0 until 7) {

            val dayWeek = diceList[i]

            val constraintLayout =
                LayoutInflater.from(context)
                    .inflate(R.layout.item_info_week, null) as ConstraintLayout

            val weekTextView = constraintLayout.findViewById<AppCompatTextView>(R.id.weekTextView)
            val dayTextView = constraintLayout.findViewById<AppCompatTextView>(R.id.dayTextView)
            val infoWeekLinear = constraintLayout.findViewById<LinearLayoutCompat>(R.id.infoWeekLinearLayout)

            infoWeekLinear.setOnClickListener { clickCallback.invoke("${CalendarDays()[i]}") }

            dayTextView.text = CalendarDays()[i]

            if (day == dayWeek) {
                pintColorThisDay(weekTextView, dayTextView, infoWeekLinear)
            }

            weekTextView.text = weekdayTreatment(dayWeek)

            linearBase.addView(constraintLayout)
        }
    }

    private fun weekdayTreatment(dayWeek: Int) = when (dayWeek) {
        Calendar.SUNDAY -> {
            "Dom"
        }
        Calendar.MONDAY -> {
            "Seg"
        }
        Calendar.TUESDAY -> {
            "Ter"
        }
        Calendar.WEDNESDAY -> {
            "Qua"
        }
        Calendar.THURSDAY -> {
            "Qui"
        }
        Calendar.FRIDAY -> {
            "Sex"
        }
        Calendar.SATURDAY -> {
            "Sab"
        }
        else -> ""
    }

    private fun pintColorThisDay(
        weekTextView: AppCompatTextView,
        dayTextView: AppCompatTextView,
        infoWeekLinear: LinearLayoutCompat
    ) {
        weekTextView.setTextColor(context.getColor(R.color.white))
        dayTextView.setTextColor(context.getColor(R.color.white))
        infoWeekLinear.background =
            context.getDrawable(R.drawable.color_green_radius)
    }

    private fun createLinearLayout(): LinearLayoutCompat =
        LinearLayoutCompat(context).apply {
            LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )

            this.orientation = LinearLayoutCompat.HORIZONTAL
        }
}



