package net.homenet.a03_07;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int SMS_RECEIVE_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            //# 권한이 있는 경우
            Toast.makeText(this, "Granted the SMS receive", Toast.LENGTH_SHORT).show();
        } else {
            //# 권한이 없는 경우
            Toast.makeText(this, "Not granted the SMS receive", Toast.LENGTH_SHORT).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                Toast.makeText(this, "A description of why you need permission", Toast.LENGTH_SHORT).show();
            }

            //# 권한요청
            ActivityCompat.requestPermissions(this
                , new String[] { Manifest.permission.RECEIVE_SMS }
                , SMS_RECEIVE_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != SMS_RECEIVE_REQUEST_CODE)
            return;

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "User approved the SMS receive permission", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User not approved the SMS receive permission", Toast.LENGTH_SHORT).show();
        }
    }
}
