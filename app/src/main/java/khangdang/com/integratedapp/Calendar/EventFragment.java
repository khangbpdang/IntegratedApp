package khangdang.com.integratedapp.Calendar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Collections;
import java.util.UUID;

import khangdang.com.integratedapp.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class EventFragment extends Fragment {
    public static final String EXTRA_EVENT_ID = "com.khangdang.calendartest5.event_id";
    private CalendarEvent mEvent;
    private EditText mTitleText;
    private EditText mDescText;
    private TimePicker mTimePicker;
    private DatePicker mDatePicker;
    private Button saveButton;
    private Button removeButton;
    private Calendar cal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hideSoftKeyboard();
        UUID eventId = (UUID)getArguments().getSerializable(EXTRA_EVENT_ID);
        mEvent = CalendarEventList.get(getActivity()).getEvent(eventId);



    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.event_creation, parent, false);

        mTitleText = (EditText)v.findViewById(R.id.event_title);
        mTitleText.setText(mEvent.getTitle());
        mTitleText.setMaxWidth(mTitleText.getWidth());
        mTitleText.setFocusable(false);
        mTitleText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mTitleText.setFocusableInTouchMode(true);
                return false;
            }
        });

        mDescText = (EditText)v.findViewById(R.id.event_description);
        mDescText.setText(mEvent.getDescription());
        mDescText.setMaxWidth(mDescText.getWidth());
        mDescText.setFocusable(false);
        mDescText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDescText.setFocusableInTouchMode(true);
                return false;
            }
        });


        mTimePicker = (TimePicker) v.findViewById(R.id.time_picker);
        mTimePicker.setHour(mEvent.getHour());
        mTimePicker.setMinute(mEvent.getMinute());
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mTitleText.setFocusable(false);
                mDescText.setFocusable(false);
            }
        });


        mDatePicker = (DatePicker) v.findViewById(R.id.date_picker);
        cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (mEvent.getDate() == null)
            mDatePicker.init(year, month, day, null);
        else
            mDatePicker.updateDate(mEvent.getYear(), mEvent.getMonth(), mEvent.getDay());

        /*
        mTitleText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mEvent.setTitle(c.toString());
            }
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                // This space intentionally left blank
            }
            public void afterTextChanged(Editable c) {
                // This one too
            }
        });

        mDescText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mEvent.setDescription(c.toString());
            }
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                // This space intentionally left blank
            }
            public void afterTextChanged(Editable c) {
                // This one too
            }
        });

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mEvent.timeConMat(hourOfDay, minute);
            }
        });

        mDatePicker.setOnDateChangedListener((new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mEvent.dateConMat(dayOfMonth, monthOfYear+1, year);
            }
        }));

        */
        saveButton = (Button) v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEvent.setTitle(mTitleText.getText().toString());
                mEvent.setDescription(mDescText.getText().toString());
                mEvent.timeConMat(mTimePicker.getHour(), mTimePicker.getMinute());
                mEvent.dateConMat(mDatePicker.getDayOfMonth(), mDatePicker.getMonth() + 1, mDatePicker.getYear());
                Collections.sort(CalendarEventList.get(getActivity()).getEvents());
                //Toast.makeText(getActivity(), "Save Button", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        removeButton = (Button) v.findViewById(R.id.delete_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarEventList.get(getActivity()).getEvents().remove(mEvent);
                getActivity().finish();
            }
        });

        //mTimePicker.setOnTimeChangedListener();
        //Using DateFormat class to format the Date object information
        //mDateButton = (Button)v.findViewById(R.id.crime_date);
        //mDateButton.setText(DateFormat.getDateInstance(DateFormat.FULL).format(mCrime.getDate()));
        //mDateButton.setEnabled(false);


        return v;
    }
    public static EventFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_EVENT_ID, crimeId);
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if(getActivity().getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public void showSoftKeyboard(View view) {

        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

}
