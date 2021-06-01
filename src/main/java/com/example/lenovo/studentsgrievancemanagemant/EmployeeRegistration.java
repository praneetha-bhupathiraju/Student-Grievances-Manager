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

public class EmployeeRegistration extends Activity {
    EditText Ename;
    EditText EID;
    EditText Epassword;
    EditText Ecnfp;
    EditText Eno;
    Button Eregister;
    TextView login;
    SQLiteDatabase db;
    DatabaseHelper openHelper;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employeeregister);
        openHelper = new DatabaseHelper(this);
        Ename = (EditText)findViewById(R.id.Ename);
        EID = (EditText)findViewById(R.id.EID);
        Epassword = (EditText)findViewById(R.id.Epassword);
        Ecnfp = (EditText)findViewById(R.id.Ecnfp);

        Eregister = (Button)findViewById(R.id.button_Eregister);
        login = (TextView)findViewById(R.id.textview_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeRegistration.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Eregister = (Button)findViewById(R.id.button_Eregister);
        Eregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getWritableDatabase();

                String name = Ename.getText().toString();
                String user = EID.getText().toString();
                String password = Epassword.getText().toString();
                String cnfPassword = Ecnfp.getText().toString();
                if(name.length() == 0 || user.length() == 0 || password.length() == 0 || cnfPassword.length() == 0) {
                    Toast.makeText(EmployeeRegistration.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    if (isValidEmail(user)) {
                        if (password.equals(cnfPassword)) {
                            cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE2_NAME+ " WHERE " + DatabaseHelper.COL_4 + "=?", new String[]{user});
                            if(cursor != null) {
                                if(cursor.getCount() > 0) {
                                    cursor.moveToNext();
                                    Toast.makeText(EmployeeRegistration.this, "Email already exists!", Toast.LENGTH_LONG).show();
                                } else {
                                    insertData(name,  user, password);
                                    Toast.makeText(EmployeeRegistration.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(EmployeeRegistration.this, "Something went wrong please try again", Toast.LENGTH_LONG).show();
                            }

                            //insertData(name, rno, user, password);
                            //Toast.makeText(EmployeeRegistration.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(EmployeeRegistration.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(EmployeeRegistration.this, "Invalid email", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    public void insertData(String name, String user, String password) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COL_2, name);
        cv.put(DatabaseHelper.COL_4, user);
        cv.put(DatabaseHelper.COL_5, password);
        long id = db.insert(DatabaseHelper.TABLE2_NAME, null, cv);
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
