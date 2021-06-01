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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class EOthers extends AppCompatActivity{
    private static final String TAG = "ListDataActivity";
    DatabaseHelper db;
    private ListView mlistView;
    private String rno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.eothers );
        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        mlistView = (ListView)findViewById(R.id.listView2);
        db = new DatabaseHelper(this);
        Cursor data = db.getData3();
        ArrayList<String> listData = new ArrayList<>();
        if(data.getCount() == 0) {
            Toast.makeText(EOthers.this, "No complaints!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                listData.add(data.getString(1));
            }
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            mlistView.setAdapter(adapter);
            mlistView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent( EOthers.this, Solution3.class );
                    String ldata = adapterView.getItemAtPosition( i ).toString();
                  //  rno = getIntent().getExtras().get( "rollNo" ).toString();
                    intent.putExtra( "ldata", ldata );
                   // intent.putExtra( "rollNo", rno );
                    startActivity( intent );
                }
            } );
        }

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( EOthers.this, EmpHomePage.class );
                    startActivity( intent );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/
                case R.id.navigation_update:
                    Intent intents = new Intent( EOthers.this, EmpUpdate.class );
                    startActivity( intents );
                    break;
                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EOthers.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(EOthers.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(EOthers.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(EOthers.this, MainActivity.class);
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
