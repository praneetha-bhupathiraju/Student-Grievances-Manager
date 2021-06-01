package com.example.lenovo.studentsgrievancemanagemant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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


public class Academics extends Activity implements View.OnClickListener{
    private static final String TAG = "Academics";
    SQLiteDatabase db;
    DatabaseHelper mdatabaseHelper;
    TextView textView;
    String dateTime;
    //EditText rno;

    EditText message;
    Button submit;


    TextView aCmpId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic);
        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        mdatabaseHelper = new DatabaseHelper(this);
        //student = new StudentRegistration(this);
        //rollnum = (EditText)findViewById( R.id.rno );
        textView = (TextView)findViewById(R.id.textView);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
        dateTime = simpleDateFormat.format(calendar.getTime());
        textView.setText(dateTime);
        //aCmpId = (TextView)findViewById(R.id.acmpId);


       // name = (StudentRegistration)findViewById( R.id.Sname );
        message = (EditText)findViewById(R.id.your_message);

        submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(this);
    }
    public void onClick(View view) {
        String msg = message.getText().toString().trim();
        //String rno = rollnum.getText().toString().trim();
        //Bundle bundle = getIntent().getExtras();
       // String name = bundle.getString( "Name" );
        //String rollNo = bundle.getString( "RollNo" );
        if(msg.length() == 0 ) {
            Toast.makeText(Academics.this, "Please enter the details", Toast.LENGTH_LONG).show();
        } else {
            addData(msg);
            //Toast.makeText(Academics.this, "Your complaint has been submitted", Toast.LENGTH_LONG).show();
            message.setText("");
        }
    }
    public void addData(String item) {
        boolean insertData = mdatabaseHelper.addData(item);
        if(insertData) {
            Toast.makeText(Academics.this,"Your response has been submitted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Academics.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( Academics.this, Homepage.class );
                    startActivity( intent );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/

                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Academics.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(Academics.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Academics.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(Academics.this, MainActivity.class);
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
