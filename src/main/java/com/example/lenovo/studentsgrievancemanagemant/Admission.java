package com.example.lenovo.studentsgrievancemanagemant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Admission extends Activity implements View.OnClickListener {
    TextView textView;
    String dateTime;
    DatabaseHelper mdatabaseHelper;
    EditText message;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admission);
        mdatabaseHelper = new DatabaseHelper( this );
        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        textView = (TextView)findViewById(R.id.textView);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
        dateTime = simpleDateFormat.format(calendar.getTime());
        textView.setText(dateTime);

        message = (EditText)findViewById(R.id.your_message);
        submit = (Button)findViewById(R.id.submit);


        submit.setOnClickListener(this);
    }
    public void onClick(View view) {
        //Bundle bundle = getIntent().getExtras();
        //String name = bundle.getString( "Name" );
        //String rollNo = bundle.getString( "RollNo" );

        String msg = message.getText().toString().trim();
        if( msg.length() == 0) {
            Toast.makeText(Admission.this, "Please eneter all the details", Toast.LENGTH_LONG).show();
        } else {
            addData(msg);
            //Toast.makeText(Admission.this, "Your response has been submitted", Toast.LENGTH_LONG).show();
        }
    }
    public void addData(String item) {
        boolean insertData = mdatabaseHelper.addData1(item);
        if(insertData) {
            Toast.makeText(Admission.this,"Your response has been submitted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Admission.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( Admission.this, Homepage.class );
                    startActivity( intent );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/

                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Admission.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(Admission.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Admission.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(Admission.this, MainActivity.class);
                            startActivity(logout);
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    break;
            }
            return false;
        }
    };
}
