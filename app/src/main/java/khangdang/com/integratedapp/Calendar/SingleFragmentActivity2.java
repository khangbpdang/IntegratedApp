package khangdang.com.integratedapp.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.TextView;

import khangdang.com.integratedapp.R;

public abstract class SingleFragmentActivity2 extends FragmentActivity {
    protected abstract Fragment createFragment();
    private TextView dateTextView;
    private Button calendarButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer2);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer2, fragment).commit();
        }
    }
}
