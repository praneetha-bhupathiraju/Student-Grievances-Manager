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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;


public class StatusCmp extends Activity {
    ListView lstView;
    Button submit;
    EditText id;
    DatabaseHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.status );
        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        lstView = (ListView) findViewById( R.id.lView );

        //submit = (Button)findViewById( R.id.submit );
        //id = (EditText)findViewById( R.id.complaints );
        db = new DatabaseHelper( this );
        final Cursor sol = db.getAcSol();
        ArrayList <String> listData = new ArrayList <>();
        if (sol.getCount() == 0) {
            //Toast.makeText(ViewCmp.this, "No complaints!", Toast.LENGTH_LONG).show();
        } else {
            while (sol.moveToNext()) {
                listData.add( sol.getString( 1 ) );
            }
            final ArrayAdapter <String> adapter = new ArrayAdapter <String>( StatusCmp.this, android.R.layout.simple_list_item_1, listData );
            lstView.setAdapter( adapter );
        }
        final Cursor s1 = db.getAdSol();
        if (s1.getCount() == 0) {
            //Toast.makeText(ViewCmp.this, "No complaints!", Toast.LENGTH_LONG).show();
        } else {
            while (s1.moveToNext()) {
                listData.add( s1.getString( 1 ) );
            }
            final ArrayAdapter <String> adapter = new ArrayAdapter <String>( StatusCmp.this, android.R.layout.simple_list_item_1, listData );
            lstView.setAdapter( adapter );
        }
        final Cursor s2 = db.getHSol();
        //ArrayList<String> listData = new ArrayList<>();
        if (s2.getCount() == 0) {
            //Toast.makeText(ViewCmp.this, "No complaints!", Toast.LENGTH_LONG).show();
        } else {
            while (s2.moveToNext()) {
                //istData.add( s2.getString(0) );
                listData.add( s2.getString( 1 ) );
            }
            final ArrayAdapter <String> adapter = new ArrayAdapter <String>( StatusCmp.this, android.R.layout.simple_list_item_1, listData );
            lstView.setAdapter( adapter );
        }
        final Cursor s3 = db.getOSol();
        //ArrayList<String> listData = new ArrayList<>();
        if (s3.getCount() == 0) {
            //Toast.makeText(ViewCmp.this, "No complaints!", Toast.LENGTH_LONG).show();
        } else {
            while (s3.moveToNext()) {
                //listData.add( s3.getString( 0 ) );
                listData.add( s3.getString( 1 ) );
            }
            final ArrayAdapter <String> adapter = new ArrayAdapter <String>( StatusCmp.this, android.R.layout.simple_list_item_1, listData );
            lstView.setAdapter( adapter );
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( StatusCmp.this, Homepage.class );
                    startActivity( intent );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/

                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StatusCmp.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(StatusCmp.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(StatusCmp.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(StatusCmp.this, MainActivity.class);
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
