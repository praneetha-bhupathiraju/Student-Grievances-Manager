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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ViewCmp extends Activity{

    DatabaseHelper db;
    ListView mlistView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcmp);


        db = new DatabaseHelper(this);

       mlistView = (ListView)findViewById( R.id.listView );
        final Cursor data = db.getData();
        ArrayList<String> listData = new ArrayList<>();
        if(data.getCount() == 0) {
            //Toast.makeText(ViewCmp.this, "No complaints!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                //listData.add(data.getString( 0 ));
                listData.add(data.getString(1));
            }
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            mlistView.setAdapter(adapter);
        }
        final Cursor data1 = db.getData1();
        if(data1.getCount() == 0) {
            //Toast.makeText(ViewCmp.this, "No complaints!", Toast.LENGTH_LONG).show();
        } else {
            while (data1.moveToNext()) {
                //listData.add(data1.getString( 0 ));
                listData.add(data1.getString(1));
            }
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            mlistView.setAdapter(adapter);
        }
        final Cursor data2 = db.getData2();
        //ArrayList<String> listData = new ArrayList<>();
        if(data2.getCount() == 0) {
            //Toast.makeText(ViewCmp.this, "No complaints!", Toast.LENGTH_LONG).show();
        } else {
            while (data2.moveToNext()) {
                //listData.add( data2.getString(0) );
                listData.add(data2.getString(1));
            }
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            mlistView.setAdapter(adapter);
        }
        final Cursor data3 = db.getData3();
        //ArrayList<String> listData = new ArrayList<>();
        if(data3.getCount() == 0) {
            //Toast.makeText(ViewCmp.this, "No complaints!", Toast.LENGTH_LONG).show();
        } else {
            while (data3.moveToNext()) {
                //listData.add( data3.getString( 0 ) );
                listData.add(data3.getString(1));
            }
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            mlistView.setAdapter(adapter);
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        /*back = (TextView)findViewById(R.id.text_back);
       back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewCmp.this, Homepage.class);
                startActivity(intent);
            }
        });*/
        //Toast.makeText( ViewCmp.this,"No complaints", Toast.LENGTH_LONG ).show();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( ViewCmp.this, Homepage.class );
                    startActivity( intent );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/

                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewCmp.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(ViewCmp.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ViewCmp.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(ViewCmp .this, MainActivity.class);
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
