package com.example.lenovo.studentsgrievancemanagemant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Solution extends Activity {
    TextView tv, tv1;
    EditText msg;
    DatabaseHelper db;
    private String ldata;
    private String rno;
    Button submit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.solution );
        // tv = (TextView)findViewById( R.id.textView );
        msg = (EditText) findViewById( R.id.your_message );
        submit = (Button) findViewById( R.id.submit );

        db = new DatabaseHelper( this );
        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
       /* submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = msg.getText().toString().trim();
                if(message.length() == 0) {
                    Toast.makeText(Solution.this, "Please enter the details", Toast.LENGTH_LONG).show();
                } else {
                    addData(message);
                    //Toast.makeText(Academics.this, "Your complaint has been submitted", Toast.LENGTH_LONG).show();
                    msg.setText("");
                }
            }
        } );*/
       /* Cursor data = db.getData();
        ArrayList<String> listData = new ArrayList<>();
        if(data.getCount() == 0) {
            Toast.makeText(Solution.this, "No complaints!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                listData.add(data.getString(1));
            }
           ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
           tv.setText(adapter);
        }*/
       submit.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String message = msg.getText().toString().trim();
               if(message.length() == 0 ) {
                   Toast.makeText(Solution.this, "Please enter the details", Toast.LENGTH_LONG).show();
               } else {
                   addData(message);
                   //Toast.makeText(Academics.this, "Your complaint has been submitted", Toast.LENGTH_LONG).show();

               }
           }
       } );

        Cursor data = db.getData();
        data.moveToFirst();

        //rno = getIntent().getExtras().get( "rollNo" ).toString();
        ldata = getIntent().getExtras().get( "ldata" ).toString();
        //int position = getIntent().getIntExtra( "position" , 0);
        tv = (TextView) findViewById( R.id.textView );
        //tv.setText( data.getString( 1 ) );
        tv.setText( ldata );
        //v1.setText( rno );
    }
    public void addData(String item) {
        boolean insertData = db.addAcSol(item);
        if(insertData) {
            Toast.makeText(Solution.this,"Your response has been submitted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Solution.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }



    /*public void addData(String item) {
        boolean insertData = db.addSolution(item);
        if(insertData) {
            Toast.makeText(Solution.this,"Your response has been submitted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Solution.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }*/
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( Solution.this, EmpHomePage.class );
                    startActivity( intent );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/
                case R.id.navigation_update:
                    Intent intents = new Intent( Solution.this, EmpUpdate.class );
                    startActivity( intents );
                    break;
                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Solution.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(Solution.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Solution.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(Solution.this, MainActivity.class);
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
