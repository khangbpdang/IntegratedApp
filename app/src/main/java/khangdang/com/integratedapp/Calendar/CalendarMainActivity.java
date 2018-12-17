package khangdang.com.integratedapp.Calendar;

import android.support.v4.app.Fragment;

public class CalendarMainActivity extends SingleFragmentActivity {

    private static final String TAG = "CalendarMainActivity";

    @Override
    protected Fragment createFragment() {
        return new CalendarMainActivityFragment();
    }

}