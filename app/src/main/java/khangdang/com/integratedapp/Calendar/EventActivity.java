package khangdang.com.integratedapp.Calendar;

import android.support.v4.app.Fragment;

import java.util.UUID;

public class EventActivity extends SingleFragmentActivity2 {
    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID)getIntent()
                .getSerializableExtra(EventFragment.EXTRA_EVENT_ID);

        return EventFragment.newInstance(crimeId);
    }
}
