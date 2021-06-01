package com.example.lenovo.studentsgrievancemanagemant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lenovo on 20-04-2020.
 */

public class Solution1 extends AppCompatActivity {
    TextView tv, tv1;
    DatabaseHelper db;
    Button submit;
    EditText msg;
    private String ldata, rno;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.solution );
        msg = (EditText)findViewById( R.id.your_message);
        // tv = (TextView)findViewById( R.id.textView );
        db = new DatabaseHelper( this );
        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        submit = (Button)findViewById( R.id.submit );
        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = msg.getText().toString().trim();
                if(message.length() == 0 ) {
                    Toast.makeText(Solution1.this, "Please enter the details", Toast.LENGTH_LONG).show();
                } else {
                    addData(message);
                    //Toast.makeText(Academics.this, "Your complaint has been submitted", Toast.LENGTH_LONG).show();

                }
            }
        } );

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
        Cursor data = db.getData1();
        data.moveToFirst();
        //rno = getIntent().getExtras().get( "rollNo" ).toString();
        //tv1 = (TextView)findViewById( R.id.tv1 );
        ldata = getIntent().getExtras().get( "ldata" ).toString();
        tv = (TextView)findViewById( R.id.textView );
        tv.setText( ldata);
        //tv1.setText( rno );
    }
    public void addData(String item) {
        boolean insertData = db.addAdSol(item);
        if(insertData) {
            Toast.makeText(Solution1.this,"Your response has been submitted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Solution1.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( Solution1.this, EmpHomePage.class );
                    startActivity( intent );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/
                case R.id.navigation_update:
                    Intent intents = new Intent( Solution1.this, EmpUpdate.class );
                    startActivity( intents );
                    break;
                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Solution1.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(Solution1.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Solution1.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(Solution1.this, MainActivity.class);
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
