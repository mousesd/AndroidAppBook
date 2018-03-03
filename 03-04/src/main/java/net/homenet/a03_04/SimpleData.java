package net.homenet.a03_04;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {
    private int number;
    private String message;

    public int getNumber() {
        return number;
    }

    public String getMessage() {
        return message;
    }

    public SimpleData(int number, String message) {
        this.number = number;
        this.message = message;
    }

    private SimpleData(Parcel source) {
        this.number = source.readInt();
        this.message = source.readString();
    }

    public static final Creator<SimpleData> CREATOR = new Creator<SimpleData>() {
        @Override
        public SimpleData createFromParcel(Parcel in) {
            return new SimpleData(in);
        }

        @Override
        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.number);
        dest.writeString(this.message);
    }
}
