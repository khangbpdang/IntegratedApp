package khangdang.com.integratedapp.Calendar;

import android.support.annotation.NonNull;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class CalendarEvent implements Comparable<CalendarEvent>{
    private UUID mId;
    private String mTitle;
    private String mDescription;
    private int mHour;
    private int mMinute;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Date date;
    private String mDate;

    private String mTime;

    public CalendarEvent() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getHour() {
        return mHour;
    }

    public void setHour(int hour) {
        this.mHour = hour;
    }

    public int getMinute() {
        return mMinute;
    }

    public void setMinute(int minute) {
        this.mMinute = minute;
    }

    public String getTime() {
        return mTime;
    }


    public int getDay() {
        return mDay;
    }

    public void setDay(int mDay) {
        this.mDay = mDay;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int mYear) {
        this.mYear = mYear;

    }

    public String getDate() {
        return mDate;
    }

    public void timeConMat(int hour, int minute) {
        setHour(hour);
        setMinute(minute);
        DecimalFormat formatter = new DecimalFormat("00");
        mTime = formatter.format(mHour) + ":" + formatter.format(mMinute);
    }

    public void dateConMat(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
        Calendar cal =  Calendar.getInstance();
        cal.set(1900 + year, month, day);
        date = cal.getTime();
        DecimalFormat formatter = new DecimalFormat("00");
        mDate = formatter.format(mMonth) + "/" + formatter.format(mDay) + "/" + mYear;
    }

    @Override
    public int compareTo(@NonNull CalendarEvent e) {
        if (e.getYear() == getYear()) {
            if (e.getMonth() == getMonth()) {
                if (e.getDay() == getDay()) {
                    if (e.getHour() == getHour()) {
                        if (e.getMinute() == getMinute()) {
                            return 0;
                        }
                        else
                        if (e.getMinute() > getMinute()) {
                            return 1;
                        }
                        else
                            return -1;
                    }
                    else
                    if (e.getHour() > getHour()) {
                        return 1;
                    }
                    else
                        return -1;
                }
                else
                if (e.getDay() > getDay()) {
                    return 1;
                }
                else
                    return -1;
            }
            else
            if (e.getMonth() > getMonth()) {
                return 1;
            }
            else
                return -1;
        }
        else
            if (e.getYear() > getYear()) {
                return 1;
            }
            else
                return -1;

    }
}
