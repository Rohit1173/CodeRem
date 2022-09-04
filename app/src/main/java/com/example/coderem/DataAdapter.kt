package com.example.coderem

import android.app.AlarmManager
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class DataAdapter(private var list: MutableList<CodeData>):RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val startTime: TextView = view.findViewById(R.id.startTime)
        val endTime: TextView = view.findViewById(R.id.endTime)
        val register: Button = view.findViewById(R.id.register)
        val countdown:TextView = view.findViewById(R.id.countdown)
        val context: Context = view.context
        val alarm:SwitchMaterial=view.findViewById(R.id.alarm)
        var countDownTimer: CountDownTimer? =null

        fun printDifferenceDateForHours(s1:String,s2:String,t:TextView) {


           // var currentTime = Calendar.getInstance().time
            val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss a", Locale.getDefault()).format(Date())

            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa", Locale.getDefault())
            val endDate1 = format.parse(s1)
            val endDate2 = format.parse(s2)
            val cur=format.parse(currentTime)

               // Toast.makeText(context, "${endDate1.time} ${endDate2.time} ${cur.time}",Toast.LENGTH_LONG).show()


            //milliseconds
            t.setTextColor(Color.parseColor("#18c73b"))
            var different = endDate1!!.time - cur!!.time
            if(different<0){
                different=endDate2!!.time- cur.time
                t.setTextColor(Color.parseColor("#de160b"))
            }

            countDownTimer = object : CountDownTimer(different, 1000) {

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
        holder.name.text=item.name
        val s1:String= change(item.start_time)
        val s2:String= change(item.end_time)

        holder.startTime.text=s1
        holder.endTime.text=s2



        holder.printDifferenceDateForHours(s1,s2,holder.countdown)
        holder.alarm.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
           if(isChecked){
              //TODO()
           }
        })


        holder.register.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(item.url)
            startActivity(holder.context,openURL,null)
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
    val dateTime: ZonedDateTime = ZonedDateTime.parse(date)

    return dateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata")).format(
        DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss a")
    )
}


