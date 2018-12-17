package khangdang.com.integratedapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import khangdang.com.integratedapp.Calendar.CalendarMainActivity;
import khangdang.com.integratedapp.YoutubeVideo.YouTubeActivity;

public class MainActivity extends AppCompatActivity {
    private Button calendarButton;
    private Button videoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarButton = (Button) findViewById(R.id.calendar);
        videoButton = (Button) findViewById(R.id.youtubeVideo);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CalendarMainActivity.class);
                startActivity(i);
            }
        });
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, YouTubeActivity.class);
                startActivity(i);
            }
        });

    }
}
