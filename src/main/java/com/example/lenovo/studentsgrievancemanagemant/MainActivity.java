package com.example.lenovo.studentsgrievancemanagemant;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity  extends AppCompatActivity {
    EditText SEmail;
    EditText Spassword;
    EditText EEmail;
    EditText EPassword;
    Button Slogin;
    Button Elogin;
    TextView Sregister;
    TextView Eregister;
    DatabaseHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper = new DatabaseHelper(this);
        SEmail = (EditText)findViewById(R.id.Edittext_Suser);
        Spassword = (EditText)findViewById(R.id.Edittext_SPassword);
        EEmail = (EditText)findViewById(R.id.Edittext_Eusername);
        EPassword = (EditText)findViewById(R.id.Edittext_Epassword);
        Sregister = (TextView)findViewById(R.id.textviwe_register);
        Sregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentRegistration.class);
                startActivity(intent);
            }
        });
        Eregister = (TextView)findViewById(R.id.textview_register);
        Eregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EmployeeRegistration.class);
                startActivity(intent);
            }
        });
        Slogin = (Button)findViewById(R.id.StudentLogin);
        Slogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = SEmail.getText().toString();
                String password = Spassword.getText().toString();
                cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+ " WHERE " + DatabaseHelper.COL_4 + "=? AND " + DatabaseHelper.COL_5 + "=?", new String[]{user,password});
                if(cursor != null) {
                    if(cursor.getCount() > 0) {
                        cursor.moveToNext();
                        Toast.makeText(MainActivity.this, "Login successfull!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, Homepage.class);
                        //intent.putExtra( "rollNo", rno );
                        intent.putExtra( "userName", user );
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        Elogin = (Button)findViewById(R.id.EmployeeLogin);
        Elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String users = EEmail.getText().toString();
                String password = EPassword.getText().toString();
                cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE2_NAME+ " WHERE " + DatabaseHelper.COL_4 + "=? AND " + DatabaseHelper.COL_5 + "=?", new String[]{users,password});
                if(cursor != null) {
                    if(cursor.getCount() > 0) {
                        cursor.moveToNext();
                        Toast.makeText(MainActivity.this, "Login successfull!", Toast.LENGTH_LONG).show();
                         Intent intent = new Intent(MainActivity.this, EmpHomePage.class);
                        //rno = getIntent().getExtras().get( "rollNo" ).toString();
                        //intent.putExtra( "rollNo", rno );
                        intent.putExtra( "users", users );
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        db = openHelper.getReadableDatabase();

    }
}
