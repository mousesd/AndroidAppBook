package net.homenet.a05_08;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Calendar;

public class MonthAdapter extends BaseAdapter {

    private static final String TAG = MonthAdapter.class.getSimpleName();
    private static final int COLUMN_COUNT = 7;

    private MonthItem[] monthItems;
    private Context context;
    private Calendar calendar;
    private int selectedPosition = -1;

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    MonthAdapter(Context context) {
        super();
        this.context = context;
        this.initialize();
    }

    private void initialize() {
        monthItems = new MonthItem[COLUMN_COUNT * 6];
        calendar = Calendar.getInstance();

        this.recalculate();
    }

    private void recalculate() {
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int firstDay = this.getFirstDay(dayOfWeek);
        Log.d(TAG, String.format("First Day: %s", firstDay));

        int lastDay = this.getMonthLastDay(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        Log.d(TAG, String.format("Last Day: %s", lastDay));

        for (int index=0; index < COLUMN_COUNT * 6; index++) {
            int day = (index + 1) - firstDay;
            if (day < 1 || day > lastDay) {
                day = 0;
            }
            monthItems[index] = new MonthItem(day);
        }
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public void setPreviousMonth() {
        calendar.add(Calendar.MONTH, -1);
        this.recalculate();
        selectedPosition = -1;
    }

    public void setNextMonth() {
        calendar.add(Calendar.MONTH, 1);
        this.recalculate();
        selectedPosition = -1;
    }

    private int getFirstDay(int dayOfWeek) {
        int firstDay = 0;
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                firstDay = 0;
                break;
            case Calendar.MONDAY:
                firstDay = 1;
                break;
            case Calendar.TUESDAY:
                firstDay = 2;
                break;
            case Calendar.WEDNESDAY:
                firstDay = 3;
                break;
            case Calendar.THURSDAY:
                firstDay = 4;
                break;
            case Calendar.FRIDAY:
                firstDay = 5;
                break;
            case Calendar.SATURDAY:
                firstDay = 6;
                break;
        }
        return firstDay;
    }

    private int getMonthLastDay(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    return 29;
                } else {
                    return 28;
                }
        }
    }

    @Override
    public int getCount() {
        return monthItems.length;
    }

    @Override
    public Object getItem(int position) {
        return monthItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new MonthItemView(context);
        }

        MonthItemView itemView = (MonthItemView) view;
        GridView.LayoutParams params = new GridView.LayoutParams(
            GridView.LayoutParams.MATCH_PARENT, 120);

        int rowIndex = position / COLUMN_COUNT;
        int columnIndex = position % COLUMN_COUNT;
        Log.d(TAG, String.format("Index: (%s, %s)", rowIndex, columnIndex));

        itemView.setItem(monthItems[position]);
        itemView.setLayoutParams(params);
        itemView.setPadding(2, 2, 2, 2);
        itemView.setGravity(Gravity.START);
        itemView.setTextColor(columnIndex == 0 ? Color.RED : Color.BLACK);
        itemView.setBackgroundColor(position == selectedPosition ? Color.YELLOW : Color.WHITE);
        return itemView;
    }
}
