package com.example.lenovo.studentsgrievancemanagemant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class EmpUpdate extends AppCompatActivity implements View.OnClickListener {
    EditText cp;
    EditText np;
    EditText cnp;
    Button update;
    DatabaseHelper db;
    private String user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.empupdate);
        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        cp = (EditText)findViewById(R.id.CP);
        np = (EditText)findViewById(R.id.NP);
        db = new DatabaseHelper( this );
        cnp = (EditText)findViewById(R.id.cnfp);
        update = (Button)findViewById(R.id.btnupdate);

        update.setOnClickListener( this );
    }

    @Override
    public void onClick(View view) {
        String CP = cp.getText().toString().trim();
        String NP = np.getText().toString().trim();
        String CNP = cnp.getText().toString().trim();
        if(NP.equals(CNP)) {
            if (CP.length() == 0 || NP.length() == 0 || CNP.length() == 0) {
                Toast.makeText(EmpUpdate.this, "Please enter all the details", Toast.LENGTH_LONG).show();
            } else {
                user = getIntent().getExtras().get( "users" ).toString();
                db.updateEntry1( NP, user );
                Toast.makeText(EmpUpdate.this, "Your password has been changed", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(EmpUpdate.this, "Your passwords does not match", Toast.LENGTH_LONG).show();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent( EmpUpdate.this, EmpHomePage.class );
                    startActivity( intent );
                    break;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;*/
                case R.id.navigation_update:
                    Intent intents = new Intent( EmpUpdate.this, EmpUpdate.class );
                    startActivity( intents );
                    break;
                case R.id.navigation_logout:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EmpUpdate.this);
                    alertDialogBuilder.setMessage("Are you sure you want to logout?");
                    alertDialogBuilder.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Toast.makeText(EmpUpdate.this,"You clicked No button",Toast.LENGTH_LONG).show();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(EmpUpdate.this, "Logout successfull", Toast.LENGTH_LONG).show();
                            Intent logout = new Intent(EmpUpdate.this, MainActivity.class);
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
