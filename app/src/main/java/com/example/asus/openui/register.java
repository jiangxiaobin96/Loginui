package com.example.asus.openui;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ASUS on 2017/6/29.
 */

public class register extends Activity {

    private EditText Register_account;
    private EditText Register_password;
    private Button register;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        dbHelper = new MyDatabaseHelper(this,"user.db",null,1);
        dbHelper.getWritableDatabase();

        Register_account = (EditText) findViewById(R.id.Register_account);
        Register_password = (EditText) findViewById(R.id.Register_password);
        register = (Button) findViewById(R.id.Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Register_account.getText().toString();
                String password = Register_password.getText().toString();
                if (!"".equals(name) && !"".equals(password)){
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("nameTable",name);
                    values.put("keyTable",password);
                    db.insert("User",null,values);
                    //Toast.makeText(getApplication(),"11111111",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });



    }

}
