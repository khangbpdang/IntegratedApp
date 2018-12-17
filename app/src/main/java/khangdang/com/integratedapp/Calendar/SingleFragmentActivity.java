package khangdang.com.integratedapp.Calendar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import khangdang.com.integratedapp.R;

public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();
    private TextView dateTextView;
    private Button calendarButton;
    Typeface typeface;
    private Calendar cal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calendar);
        dateTextView = (TextView) findViewById(R.id.date_tv);
        calendarButton = (Button) findViewById(R.id.calendarButton);
        cal = Calendar.getInstance();
        //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


        Intent otherIntent = getIntent();
        String date = otherIntent.getStringExtra("date");


        //dateTextView.setText(currentDateTimeString);


        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleFragmentActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });



        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }




    }


}
