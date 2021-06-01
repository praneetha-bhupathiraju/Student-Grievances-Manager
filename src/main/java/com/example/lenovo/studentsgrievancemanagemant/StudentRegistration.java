package com.example.lenovo.studentsgrievancemanagemant;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentRegistration extends Activity {
     EditText Sname;
     //EditText Srno;
    EditText SID;
    EditText Spassword;
    EditText Scnfp;
    Button Sregister;
    TextView login;
    SQLiteDatabase db;
    DatabaseHelper openHelper;
    String st;
    Cursor cursor;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentregister);
        openHelper = new DatabaseHelper(this);
        Sname = (EditText)findViewById(R.id.Sname);
        String name = Sname.getText().toString();
        //Srno = (EditText)findViewById(R.id.Srno);
        //String rollNo = Srno.getText().toString();
        SID = (EditText) findViewById(R.id.SID);
        Spassword = (EditText)findViewById(R.id.Edittext_SPassword);
        Scnfp = (EditText)findViewById(R.id.Scnfp);
        login = (TextView)findViewById(R.id.textview_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentRegistration.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Sregister = (Button)findViewById(R.id.button_Sregister);
        Sregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getWritableDatabase();

                String name = Sname.getText().toString();
                //String rno = Srno.getText().toString();
                String user = SID.getText().toString();
                String password = Spassword.getText().toString();
                String cnfPassword = Scnfp.getText().toString();
                if(name.length() == 0 || user.length() == 0 || password.length() == 0 || cnfPassword.length() == 0) {
                    Toast.makeText(StudentRegistration.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    if (isValidEmail(user)) {
                        if (password.equals(cnfPassword)) {
                            cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+ " WHERE " + DatabaseHelper.COL_4 + "=?", new String[]{user});
                            if(cursor != null) {
                                if(cursor.getCount() > 0) {
                                    cursor.moveToNext();
                                    Toast.makeText(StudentRegistration.this, "Email already exists!", Toast.LENGTH_LONG).show();
                                } else {
                                    insertData(name, user, password);
                                    Toast.makeText(StudentRegistration.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent( StudentRegistration.this, MainActivity.class );
                                   // intent.putExtra( "rollNo", rno );
                                }
                            } else {
                                Toast.makeText(StudentRegistration.this, "Something went wrong please try again", Toast.LENGTH_LONG).show();
                            }


                        } else {
                            Toast.makeText(StudentRegistration.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(StudentRegistration.this, "Invalid email", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
       /* Bundle bundle = new Bundle( );
        Intent i = new Intent( StudentRegistration.this, Academics.class );
        bundle.putString( "Name", name );
        bundle.putString( "RollNo", rollNo );
       // startActivity( i );
        Intent i1 = new Intent( StudentRegistration.this, Admission.class );
        bundle.putString( "Name", name );
        bundle.putString( "RollNo", rollNo );
        //startActivity( i1 );
        Intent i2 = new Intent( StudentRegistration.this, Hostel.class );
        bundle.putString( "Name", name );
        bundle.putString( "RollNo", rollNo );
        //startActivity( i2 );
        Intent i3 = new Intent( StudentRegistration.this, Others.class );
        bundle.putString( "Name", name );
        bundle.putString( "RollNo", rollNo );
        //startActivity( i3 );*/


    }
    public void insertData(String name, String user, String password) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COL_2, name);
        //cv.put(DatabaseHelper.COL_3, rno);
        cv.put(DatabaseHelper.COL_4, user);
        cv.put(DatabaseHelper.COL_5, password);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, cv);
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
