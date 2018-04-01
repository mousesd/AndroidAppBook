package net.homenet.a05_07;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Calendar;

public class DateTimePicker extends LinearLayout {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private CheckBox checkBox;
    private OnDateTimeChangedListener listener;

    public DateTimePicker(Context context) {
        super(context);
        this.initailize(context);
    }

    public DateTimePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initailize(context);
    }

    private void initailize(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null)
            return;

        inflater.inflate(R.layout.date_time_picker, this, true);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        datePicker = this.findViewById(R.id.datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                if (listener != null) {
                    listener.onDateTimeChanged(DateTimePicker.this, year, monthOfYear, dayOfMonth
                        , timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                }
            }
        });

        checkBox = this.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                timePicker.setEnabled(isChecked);
                timePicker.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            }
        });

        timePicker = this.findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                if (listener != null) {
                    listener.onDateTimeChanged(DateTimePicker.this, datePicker.getYear()
                            , datePicker.getMonth(), datePicker.getDayOfMonth(), hourOfDay, minute);
                }
            }
        });
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
        timePicker.setEnabled(checkBox.isChecked());
        timePicker.setVisibility(checkBox.isChecked() ? View.VISIBLE : View.INVISIBLE);
    }

    public void setOnDateTimeChangedListener(OnDateTimeChangedListener listener) {
        this.listener = listener;
    }

    public void updateTimeTime(int year, int monthOfYear, int dayOfMonth, int hour, int minute) {
        datePicker.updateDate(year, monthOfYear, dayOfMonth);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
    }

    public int getYear() {
        return datePicker.getYear();
    }

    public int getMonth() {
        return datePicker.getMonth();
    }

    public int getDayOfMonth() {
        return datePicker.getDayOfMonth();
    }

    public int getHour() {
        return timePicker.getCurrentHour();
    }

    public int getMinute() {
        return timePicker.getCurrentMinute();
    }

    public interface OnDateTimeChangedListener {
        void onDateTimeChanged(DateTimePicker dateTimePicker, int year, int monthOfYear, int dayOfYear
            , int hourOfDay, int minute);
    }
}
