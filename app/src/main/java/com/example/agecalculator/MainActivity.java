package com.example.agecalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText date = findViewById(R.id.editText);
        Button get  = findViewById(R.id.material_text_button);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bday = date.getText().toString();



                if (!TextUtils.isEmpty(bday)){

                    Pattern p = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
                    Matcher m = p.matcher(bday);

                    if(m.find())
                    {
                        Calendar c = Calendar.getInstance();

                        int curD = c.get(Calendar.DAY_OF_MONTH);
                        int curM = c.get(Calendar.MONTH);
                        int curY = c.get(Calendar.YEAR);

                        String[] ch = bday.split("/");

                        int day = Integer.parseInt(ch[0]);
                        int month = Integer.parseInt(ch[1]);
                        int year = Integer.parseInt(ch[2]);

                        int age = curY - year - 1;


                        if (curM > month)
                        {
                            ++age;
                        }
                        else if (curM == month)
                        {
                            if (curD > day)
                                ++age;
                        }

                         AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                                alertDialog.setTitle("YOUR AGE IS : ");
                                alertDialog.setMessage(Integer.toString(age));
                                alertDialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });


                                AlertDialog dialog  = alertDialog.create();
                                dialog.show();

                    }
                    else
                        Toast.makeText(getApplicationContext(),"PLEASE ENTER YOUR DOB IN dd/mm/yyyy FORMAT",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"PLEASE ENTER YOUR DOB",Toast.LENGTH_LONG).show();
            }
        });

       }



}
