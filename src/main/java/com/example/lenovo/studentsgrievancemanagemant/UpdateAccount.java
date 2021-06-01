package com.example.lenovo.studentsgrievancemanagemant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

/**
 * Created by Lenovo on 14-04-2020.
 */

public class UpdateAccount extends Activity implements View.OnClickListener {
    EditText cp;
    EditText np;
    EditText cnp;
    Button update;
    TextView back;
    DatabaseHelper db;
    private String userName;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.updateaccount);
        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        cp = (EditText)findViewById(R.id.CP);
        db = new DatabaseHelper( this );
        np = (EditText)findViewById(R.id.NP);
        cnp = (EditText)findViewById(R.id.cnfp);
        update = (Button)findViewById(R.id.btnupdate);

        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String CP = cp.getText().toString().trim();
        String NP = np.getText().toString().trim();
        String CNP = cnp.getText().toString().trim();
        if(NP.equals(CNP)) {
            if (CP.length() == 0 || NP.length() == 0 || CNP.length() == 0) {
                Toast.makeText(UpdateAccount.this, "Please enter all the details", Toast.LENGTH_LONG).show();
            } else {
                //Cursor cursor = db.updateEntry(  );
                //Cursor ("SELECT * FROM Employee WHERE EmpName='"+ editsearchname.getText()+"'", null);
                userName = getIntent().getExtras().get( "userName" ).toString();

                db.updateEntry( NP, userName );
                Toast.makeText(UpdateAccount.this, "Your password has been changed", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(UpdateAccount.this, "Your passwords does not match", Toast.LENGTH_LONG).show();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( UpdateAccount.this, Homepage.class );
                    startActivity( intent );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/
                
                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UpdateAccount.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(UpdateAccount.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(UpdateAccount.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(UpdateAccount.this, MainActivity.class);
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
