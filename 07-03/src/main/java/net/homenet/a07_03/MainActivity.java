package net.homenet.a07_03;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText1, editText2;
    private ProcessThread thread = new ProcessThread();
    private MainHandler mainHandler = new MainHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = this.findViewById(R.id.editText1);
        editText2 = this.findViewById(R.id.editText2);
        Button button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editText1.getText().toString();
                Message sendMessage = Message.obtain();
                sendMessage.obj = message;

                thread.getHandler().sendMessage(sendMessage);
            }
        });

        thread.start();
    }

    private class ProcessThread extends Thread {

        private ProcessHandler handler;

        public ProcessHandler getHandler() {
            return handler;
        }

        ProcessThread() {
            this.handler = new ProcessHandler();
        }

        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }
    }

    @SuppressLint("HandlerLeak")
    private class ProcessHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Message message = Message.obtain();
            message.obj = msg.obj + " Mike!!!";
            mainHandler.sendMessage(message);
        }
    }

    @SuppressLint("HandlerLeak")
    private class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String message = (String) msg.obj;
            editText2.setText(message);
        }
    }
}
