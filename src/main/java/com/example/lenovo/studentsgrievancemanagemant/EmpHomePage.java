package com.example.lenovo.studentsgrievancemanagemant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public  class EmpHomePage extends AppCompatActivity{
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ehomepage );
        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        CardView v1, v2, v3, v4;
        //mTextMessage = (TextView) findViewById( R.id.message );
        v1 = (CardView)findViewById(R.id.cardView1);
        v2 = (CardView)findViewById(R.id.cardView2);
        v3 = (CardView)findViewById(R.id.cardView3);
        v4 = (CardView)findViewById(R.id.cardView4);
        //rno = getIntent().getExtras().get( "rollNo" ).toString();
        v1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( EmpHomePage.this, EAcademics.class );
                //intent.putExtra( "rollNo", rno );
                startActivity( intent );
            }
        } );
        v2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( EmpHomePage.this, EAdmission.class );
                //intent.putExtra( "rollNo", rno );
                startActivity( intent );
            }
        } );
        v3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( EmpHomePage.this, EHostel.class );
               // intent.putExtra( "rollNo", rno );
                startActivity( intent );
            }
        } );
        v4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( EmpHomePage.this, EOthers.class );
                //intent.putExtra( "rollNo", rno );
                startActivity( intent );
            }
        } );

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( EmpHomePage.this, EmpHomePage.class );
                    startActivity( intent );
                    break;
                case R.id.navigation_update:
                    Intent intents = new Intent( EmpHomePage.this, EmpUpdate.class );
                    user = getIntent().getExtras().get( "users" ).toString();
                    intents.putExtra( "users", user );
                    startActivity( intents );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/
                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EmpHomePage.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(EmpHomePage.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(EmpHomePage.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(EmpHomePage.this, MainActivity.class);
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
