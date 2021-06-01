package com.example.lenovo.studentsgrievancemanagemant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

public class Homepage extends AppCompatActivity {

    private TextView mTextMessage;
    private ListView mListView;
    private ArrayAdapter aAdapter;
    CardView v1, v2, v3, v4;
    TextView logout;
    private String userName;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( Homepage.this, Homepage.class );
                    startActivity( intent );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/
                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Homepage.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(Homepage.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Homepage.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(Homepage.this, MainActivity.class);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_homepage );
        ListView mListView;
        ArrayAdapter aAdapter;
        CardView v1, v2, v3, v4;
        //mTextMessage = (TextView) findViewById( R.id.message );
        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        v1 = (CardView)findViewById(R.id.cardView1);
        v2 = (CardView)findViewById(R.id.cardView2);
        v3 = (CardView)findViewById(R.id.cardView3);
        v4 = (CardView)findViewById(R.id.cardView4);
        v1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Homepage.this, RegisterCmp.class );
                //userName = getIntent().getExtras().get( "userName" ).toString();
                //intent.putExtra( "userName", userName );
                startActivity( intent );
            }
        } );
        v2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Homepage.this, ViewCmp.class );
                startActivity( intent );
            }
        } );
        v3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Homepage.this, StatusCmp.class );
                startActivity( intent );
            }
        } );
        v4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Homepage.this, UpdateAccount.class );
                userName = getIntent().getExtras().get( "userName" ).toString();
                intent.putExtra( "userName", userName );
                startActivity( intent );
            }
        } );

    }

}
