package net.homenet.a03_03;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = this.findViewById(R.id.editText);
        Button button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(editText.getText().toString()));
                startActivity(intent);
            }
        });

        Button openPdfButton = this.findViewById(R.id.openPdf);
        openPdfButton.setOnClickListener(new View.OnClickListener() {
            private final String FILE_NAME = "Sample.pdf";

            @Override
            public void onClick(View v) {
                String sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                String filePath = sdCardPath + File.separator + FILE_NAME;
                File pdfFile = new File(filePath);

                if (pdfFile.exists()) {
                    Uri uri = Uri.fromFile(pdfFile);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "application/pdf");

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "No pdf app's", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No exist file", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
