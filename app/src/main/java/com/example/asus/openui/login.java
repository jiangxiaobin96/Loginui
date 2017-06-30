package com.example.asus.openui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity {

    private EditText Login_account;
    private EditText Login_password;
    private Button Login;
    private Button Login_register;
    private Button Login_forget;
    private MyDatabaseHelper dbHelper;
    private String input_name;
    private String input_key;
    private int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        //建立数据库
        dbHelper = new MyDatabaseHelper(this,"user.db",null,1);
        dbHelper.getWritableDatabase();

        Login_account = (EditText) findViewById(R.id.Login_account);
        Login_password = (EditText) findViewById(R.id.Login_password);
        Login = (Button) findViewById(R.id.Login);
        Login_register = (Button) findViewById(R.id.Login_register);


        //注册
        Login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,register.class);
                startActivity(intent);
            }
        });

        //登陆
        x = -1;

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_name = Login_account.getText().toString();
                //Toast.makeText(getApplication(),"输入的数据是："+Login_name,Toast.LENGTH_SHORT).show();
                input_key = Login_password.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("User",null,null,null,null,null,null);
                //int x = -1;
                if (cursor.moveToFirst()){
                    do {
                        String nameSQL = cursor.getString(cursor.getColumnIndex("nameTable"));
                        //Toast.makeText(getApplication(),"数据库中数据是："+nameSQL,Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplication(),"输入的数据是："+input_name,Toast.LENGTH_SHORT).show();
                        String keySQL = cursor.getString(cursor.getColumnIndex("keyTable"));
                        /*if (input_name.equals(nameSQL) == true){
                            Toast.makeText(getApplication(),"==",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplication(),"!=",Toast.LENGTH_SHORT).show();
                        }*/
                        if (nameSQL.equals(input_name)){
                            x = 0;
                            //Toast.makeText(getApplication(),"1111111",Toast.LENGTH_SHORT).show();
                            if (keySQL.equals(input_key)){
                                //Toast.makeText(getApplication(),"222222",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(login.this,welcome.class);
                                startActivity(intent);
                                break;
                            } else {
                                Toast.makeText(getApplication(),"wrong password",Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    } while (cursor.moveToNext());
                }
                if (x == -1){
                    Toast.makeText(getApplication(),"wrong name",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
