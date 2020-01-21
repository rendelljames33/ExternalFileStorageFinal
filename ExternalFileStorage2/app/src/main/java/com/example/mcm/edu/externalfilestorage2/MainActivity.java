package com.example.mcm.edu.externalfilestorage2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;


import android.os.Environment;

public class MainActivity extends AppCompatActivity {
    static final int READ_BLOCK_SIZE = 100;
    final TextView wc = findViewById(R.id.textView);
    final EditText textbox = findViewById(R.id.editText);
    final Button read = findViewById(R.id.btnread);
    final Button clear = findViewById(R.id.btnclear);
    final Button write = findViewById(R.id.btnwrite);
    File akongfile;
    String akongGitype;
    private String filename="moreno.test";
    private String filepath="moreno_externalio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        read.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            FileInputStream fileIn = openFileInput("mytextfile.txt");
                            InputStreamReader InputRead = new InputStreamReader(fileIn);

                            char[] inputBuffer = new char[READ_BLOCK_SIZE];
                            String s = "";
                            int charRead;

                            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                                // char to string conversion
                                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                                s += readstring;
                            }
                            InputRead.close();
                            textbox.setText(s);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
        );
        clear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textbox.setText("");
                    }
                }
        );
        write.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(textbox.getText().toString());
                            outputWriter.close();

                            //display file saved message
                            Toast.makeText(getBaseContext(), "File saved successfully!",
                                    Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getBaseContext(), "File saved successfully!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            wc.setEnabled(false);
        }
    }
        private static boolean isExternalStorageReadOnly() {
            String extStorageState = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
                return true;
            }
            return false;
        }

        private static boolean isExternalStorageAvailable() {
            String extStorageState = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
                return true;
            }
            return false;
        }
    }
