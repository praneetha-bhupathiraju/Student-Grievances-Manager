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

public class Solution2 extends AppCompatActivity{
    TextView tv, tv1;
    DatabaseHelper db;
    EditText msg;
    Button submit;
    private String ldata, rno;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.solution );
        // tv = (TextView)findViewById( R.id.textView );
        db = new DatabaseHelper( this );
        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        msg = (EditText)findViewById( R.id.your_message );
        submit = (Button)findViewById( R.id.submit );

        Cursor data = db.getData2();
        data.moveToFirst();
        //rno = getIntent().getExtras().get( "rollNo" ).toString();
        ldata = getIntent().getExtras().get( "ldata" ).toString();
        //tv1 = (TextView)findViewById( R.id.tv1 );
        tv = (TextView)findViewById( R.id.textView );
        tv.setText( ldata );
       // tv1.setText( rno );
        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = msg.getText().toString().trim();
                if(message.length() == 0 ) {
                    Toast.makeText(Solution2.this, "Please enter the details", Toast.LENGTH_LONG).show();
                } else {
                    addData(message);
                    //Toast.makeText(Academics.this, "Your complaint has been submitted", Toast.LENGTH_LONG).show();

                }
            }
        } );
    }
    public void addData(String item) {
        boolean insertData = db.addHSol(item);
        if(insertData) {
            Toast.makeText(Solution2.this,"Your response has been submitted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Solution2.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( Solution2.this, EmpHomePage.class );
                    startActivity( intent );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/
                case R.id.navigation_update:
                    Intent intents = new Intent( Solution2.this, EmpUpdate.class );
                    startActivity( intents );
                    break;
                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Solution2.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(Solution2.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Solution2.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(Solution2.this, MainActivity.class);
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
