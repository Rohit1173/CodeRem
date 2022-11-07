package com.example.coderem

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class DataAdapter(private var list: MutableList<CodeData>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val startTime: TextView = view.findViewById(R.id.startTime)
        val endTime: TextView = view.findViewById(R.id.endTime)
        val register: Button = view.findViewById(R.id.register)
        val countdown: TextView = view.findViewById(R.id.countdown)
        val context: Context = view.context
        val checkB: AppCompatCheckBox = view.findViewById(R.id.checkbox)
        val site: TextView = view.findViewById(R.id.site)
        var countDownTimer: CountDownTimer? = null

        val settings: SharedPreferences = context.getSharedPreferences("mysettings", 0)
        val editor = settings.edit()

        fun printDifferenceDateForHours(s1: String, s2: String, t: TextView) {

            // var currentTime = Calendar.getInstance().time
            val currentTime =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss a", Locale.getDefault()).format(Date())

            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa", Locale.getDefault())
            val endDate1 = format.parse(s1)
            val endDate2 = format.parse(s2)
            val cur = format.parse(currentTime)


            //milliseconds
            t.setTextColor(Color.parseColor("#18c73b"))
            var different = endDate1!!.time - cur!!.time
            if (different < 0) {
                different = endDate2!!.time - cur.time
                t.setTextColor(Color.parseColor("#de160b"))
            }

            countDownTimer = object : CountDownTimer(different, 1000) {

                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    var diff = millisUntilFinished
                    val secondsInMilli: Long = 1000
                    val minutesInMilli = secondsInMilli * 60
                    val hoursInMilli = minutesInMilli * 60
                    val daysInMilli = hoursInMilli * 24

                    val elapsedDays = diff / daysInMilli
                    diff %= daysInMilli

                    val elapsedHours = diff / hoursInMilli
                    diff %= hoursInMilli

                    val elapsedMinutes = diff / minutesInMilli
                    diff %= minutesInMilli

                    val elapsedSeconds = diff / secondsInMilli

                    t.text = "$elapsedDays:$elapsedHours:$elapsedMinutes:$elapsedSeconds"
                }

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    t.text = "done!"
                }
            }.start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]
        holder.name.text = item.name
        holder.site.text = item.site
        val str1: String = item.start_time
        val str2: String = item.end_time
        var s1: String = str1
        var s2: String = str2
        if ((item.start_time.length > 12 && item.start_time[10] == ' ') || (item.end_time.length > 12 && item.end_time[10] == ' ')) {
            val conv: Long = 19800000
            var a1 = "am"
            var a2 = "am"
            val k1: Int = (item.start_time[11] - '0') * 10
            val m1: Int = item.start_time[12] - '0'
            val ans1: Int = k1 + m1
            if (ans1 >= 12) {
                a1 = "pm"
            }

            val k2: Int = (item.end_time[11] - '0') * 10
            val m2: Int = item.end_time[12] - '0'
            val ans2: Int = k2 + m2
            if (ans2 >= 12) {
                a2 = "pm"
            }

            s1 = s1.substring(0, s1.length - 3) + a1
            s2 = s2.substring(0, s2.length - 3) + a2

            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa", Locale.getDefault())
            val e1 = format.parse(s1)
            val e2 = format.parse(s2)

            val t1: Long = e1!!.time + conv
            val t2: Long = e2!!.time + conv

            val r1 = Date(t1)
            val r2 = Date(t2)
            s1 = format.format(r1)
            s2 = format.format(r2)


            holder.startTime.text = s1
            holder.endTime.text = s2
        } else {
            s1 = change(str1)
            s2 = change(str2)
            holder.startTime.text = s1
            holder.endTime.text = s2
        }

        holder.printDifferenceDateForHours(s1, s2, holder.countdown)

holder.checkB.isChecked=holder.settings.getBoolean(holder.name.text.toString(),false)

        holder.checkB.setOnCheckedChangeListener { _, isChecked ->

            val checkBoxValue: Boolean = isChecked
            holder.editor.putBoolean(holder.name.text.toString(), checkBoxValue)
            holder.editor.commit()

            if (isChecked) {
                Toast.makeText(
                    holder.context,
                    "Notif Enbaled for" + holder.name.text.toString(),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    holder.context,
                    "Notif Disabled for" + holder.name.text.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        holder.register.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(item.url)
            startActivity(holder.context, openURL, null)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        //add cancel function
        if (holder.countDownTimer != null) {
            holder.countDownTimer!!.cancel()
        }
    }

}

//private fun diff(
//    fromDate: String,
//    toDate: String,
//    formatter: String = "yyyy-MM-dd HH:mm:ss a",
//    locale: Locale = Locale.getDefault()
//): String {
//
//    val fmt = SimpleDateFormat(formatter, locale)
//    val bgn = fmt.parse(fromDate)
//    val end = fmt.parse(toDate)
//
//    val millis = end!!.time - bgn!!.time
//    val seconds: Long = millis / 1000
//    val minutes = seconds / 60
//    val hours = minutes / 60
//    val days = hours / 24
//    return days.toString() + ":" + hours % 24 + ":" + minutes % 60 + ":" + seconds % 60
//}

@RequiresApi(Build.VERSION_CODES.O)
private fun change(date: String): String {
    Log.d("TAG", date)
    val dateTime: ZonedDateTime = ZonedDateTime.parse(date)


    val s = dateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata")).format(
        DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss a")
    ).toString()

    return s
}


