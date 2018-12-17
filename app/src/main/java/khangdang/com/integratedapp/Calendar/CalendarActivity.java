package khangdang.com.integratedapp.Calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.TextView;

import khangdang.com.integratedapp.R;

public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";

    private CalendarView mCalendarView;
    private TextView currentDate;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        currentDate = (TextView) findViewById(R.id.current_date);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                currentDate.setText(date);
                //Intent intent = new Intent(CalendarActivity.this, CalendarMainActivity.class);
                //intent.putExtra("date", date);
                //startActivity(intent);
            }
        });
    }
}
