package khangdang.com.integratedapp.Calendar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ListFragment;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import khangdang.com.integratedapp.R;

public class CalendarMainActivityFragment extends ListFragment {
    private static final String TAG = "CalendarActivity";
    private ArrayList<CalendarEvent> mEvents;
    private Button addButton;
    private Button removeButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addButton = (Button) getActivity().findViewById(R.id.add_button);
        removeButton = (Button) getActivity().findViewById(R.id.delete_button);
        mEvents = CalendarEventList.get(getActivity()).getEvents();
        Collections.sort(mEvents);

        final EventAdapter adapter = new EventAdapter(mEvents);
        setListAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarEventList.get(getActivity()).addEvent();
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        CalendarEvent e = ((EventAdapter)getListAdapter()).getItem(position);
        // Start EventActivity
        Intent i = new Intent(getActivity(), EventActivity.class);
        i.putExtra(EventFragment.EXTRA_EVENT_ID, e.getId());
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((EventAdapter)getListAdapter()).notifyDataSetChanged();
    }

    public void addEvent() {
        CalendarEvent e = new CalendarEvent();
        e.setTitle("New Event added via ADD BUTTON");

        mEvents.add(e);
    }

    public class EventAdapter extends ArrayAdapter<CalendarEvent> {
        public EventAdapter(ArrayList<CalendarEvent> events) {
            super(getActivity(), 0, events);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.event_list_item, null);
            }

            // Configure the view for this CalendarEvent
            final CalendarEvent e = getItem(position);
            TextView titleTextView = (TextView)convertView.findViewById(R.id.event_list_title);
            titleTextView.setText(e.getTitle());
            TextView timeTextView = (TextView)convertView.findViewById(R.id.event_list_time);
            // Uses DateFormat class to format Date objects' information
            timeTextView.setText(e.getTime());
            TextView dateTextView = (TextView)convertView.findViewById(R.id.event_list_date);
            dateTextView.setText(e.getDate());
            final TextView descTextView = (TextView)convertView.findViewById(R.id.event_list_desc);
            descTextView.setText(e.getDescription());
            descTextView.post(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void run() {
                    // Past the maximum number of lines we want to display.
                    if (descTextView.getLineCount() > 2) {
                        int lastCharShown = descTextView.getLayout().getLineVisibleEnd(2 - 1);

                        descTextView.setMaxLines(2);

                        //String moreString = getContext().getString(R.string.more);
                        //String suffix = "  ";

                        // 3 is a "magic number" but it's just basically the length of the ellipsis we're going to insert
                        String actionDisplayText = e.getDescription().substring(0, lastCharShown - 3) + "...";

                        SpannableString truncatedSpannableString = new SpannableString(actionDisplayText);
                        //int startIndex = actionDisplayText.indexOf(moreString);
                        //truncatedSpannableString.setSpan(new ForegroundColorSpan(getContext().getColor(R.color.blueish)), startIndex, startIndex + moreString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        descTextView.setText(truncatedSpannableString);
                    }
                }
            });



            return convertView;
        }
    }
}
