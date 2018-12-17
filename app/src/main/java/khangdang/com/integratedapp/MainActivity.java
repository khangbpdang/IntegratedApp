package khangdang.com.integratedapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import khangdang.com.integratedapp.Calendar.CalendarMainActivity;
import khangdang.com.integratedapp.News.NewsActivity;
import khangdang.com.integratedapp.YoutubeVideo.YouTubeActivity;

public class MainActivity extends AppCompatActivity {
    private TextView appName;
    private Button calendarButton;
    private Button videoButton;
    private Button newsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appName = (TextView) findViewById(R.id.app_name);
        calendarButton = (Button) findViewById(R.id.calendar);
        videoButton = (Button) findViewById(R.id.youtubeVideo);
        newsButton = (Button) findViewById(R.id.news_button);

        // Blinking app name
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1500); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        appName.startAnimation(anim);

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

        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(i);
            }
        });
    }
}
