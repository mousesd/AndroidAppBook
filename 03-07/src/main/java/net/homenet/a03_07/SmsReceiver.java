package net.homenet.a03_07;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("MalformedFormatString")
public class SmsReceiver extends BroadcastReceiver {

    private static final String Tag = SmsReceiver.class.getSimpleName();

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(Tag, "onReceive()");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = this.parseSmsMessage(bundle);
        if (messages != null && messages.length > 0) {
            String sender = messages[0].getOriginatingAddress();
            Log.d(Tag, String.format("SMS Sender: %s", sender));

            String contents = messages[0].getMessageBody();
            Log.d(Tag, String.format("SMS Contents: %s", contents));

            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.d(Tag, String.format("SMS received date: %s", receivedDate.toString()));

            //# SmsActivity로 SMS 수신정보 전달
            Intent args = new Intent(context, SmsActivity.class);
            args.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            args.putExtra("sender", sender);
            args.putExtra("content", contents);
            args.putExtra("receivedDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(receivedDate));
            context.startActivity(args);
        }
    }

    @Nullable
    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objects = (Object[]) bundle.get("pdus");
        if (objects == null)
            return null;

        SmsMessage[] messages = new SmsMessage[objects.length];
        for (int index = 0; index < objects.length; index++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[index] = SmsMessage.createFromPdu((byte[]) objects[index], format);
            } else {
                messages[index] = SmsMessage.createFromPdu((byte[]) objects[index]);
            }
        }

        return messages;
    }
}
