package net.homenet.a03_06;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {

    private static final String Tag = MainService.class.getName();

    public MainService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Tag, "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Tag, "onDestroy()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Tag, "onStartCommand");

        if (intent == null)
            return Service.START_STICKY;

        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");
        Log.d(Tag, String.format("command: %s, name: %s", command, name));

        for (int count = 0; count < 5; count++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(Tag, String.format("Waiting %d seconds", count));
        }

        Intent args = new Intent(getApplicationContext(), MainActivity.class);
        args.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP
            | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        args.putExtra("command", "show");
        args.putExtra("name", name + " from a service");
        startActivity(args);

        return super.onStartCommand(intent, flags, startId);
    }
}
