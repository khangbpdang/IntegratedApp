package khangdang.com.integratedapp.Calendar;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class CalendarEventList {
    private ArrayList<CalendarEvent> mEvents;
    private static CalendarEventList calendarEventList;
    private Context mAppContext;
    private CalendarEventList(Context appContext) {
        mAppContext = appContext;
        mEvents = new ArrayList<CalendarEvent>();

        for (int i = 0; i < 5; i++) {
            CalendarEvent e = new CalendarEvent();
            e.setTitle("New Event");

            mEvents.add(e);
        }
    }
    public static CalendarEventList get(Context c) {
        if (calendarEventList == null) {
            calendarEventList = new CalendarEventList(c.getApplicationContext());
        }
        return calendarEventList;
    }
    public ArrayList<CalendarEvent> getEvents() {
        return mEvents;
    }
    public CalendarEvent getEvent(UUID id) {
        for (CalendarEvent e : mEvents) {
            if (e.getId().equals(id))
                return e;
        }
        return null;
    }

    public void sortByDate() {
        Collections.sort(mEvents);
    }

    public void addEvent(CalendarEvent e) {
        e.setTitle("New Event added via ADD BUTTON");
        mEvents.add(e);
    }

    public CalendarEvent removeEvent(CalendarEvent e) {
        for (int i = 0; i < mEvents.size() - 1; i++) {
            if (e.getId().equals(mEvents.get(i).getId())) {
                mEvents.remove(mEvents.get(i));
            }
        }
        return e;
    }
    public void addEvent() {
        CalendarEvent e = new CalendarEvent();
        e.setTitle("New Event added via ADD BUTTON");

        mEvents.add(e);
    }
}
