package com.example.shallwhite.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private UsersDatabaseHelper mDatabaseHelper;
    public static boolean isLogin = false;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();//设置登录标题栏
        actionBar.setTitle("登录");

        if (savedInstanceState != null){//如果之前登录过就直接跳转到主页面，并且这时按BACK可以回到登录界面
            isLogin = savedInstanceState.getBoolean("is_login");
            if (isLogin){
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        }

        final EditText accountEdit = (EditText)findViewById(R.id.account_edit);
        final EditText passwordEdit = (EditText)findViewById(R.id.password_edit);
        Button register = (Button)findViewById(R.id.register);
        Button login = (Button)findViewById(R.id.login);
        final CheckBox remember_password = (CheckBox)findViewById(R.id.remember_password);
        mDatabaseHelper = new UsersDatabaseHelper(this,"Users.db",null,1);//建表

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);//记住密码
        boolean isRemember = mSharedPreferences.getBoolean("remember_password",false);
        if (isRemember){
            accountEdit.setText(mSharedPreferences.getString("account",null));
            passwordEdit.setText(mSharedPreferences.getString("password",null));
            remember_password.setChecked(true);
        }else {
            accountEdit.setText(null);
            passwordEdit.setText(null);
        }

        register.setOnClickListener(new View.OnClickListener() {//实现注册功能
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = mDatabaseHelper.getWritableDatabase();
                Cursor cursor = database.query("User", null, null, null, null, null, null);
                boolean flag = false;
                if (cursor.moveToFirst()) {
                    do {
                        String account = cursor.getString(cursor.getColumnIndex("account"));
                        if (account.equals(accountEdit.getText().toString())) {
                            flag = true;
                        }
                    } while (cursor.moveToNext());
                    if (!flag) {
                        if (accountEdit.getText() != null && accountEdit.getText().length() >= 6 && passwordEdit.getText() != null && passwordEdit.getText().length() >= 8) {
                            Users users = new Users();
                            users.setAccount(accountEdit.getText().toString());
                            users.setPassword(passwordEdit.getText().toString());
                            ContentValues values = new ContentValues();
                            values.put("account", users.getAccount());
                            values.put("password", users.getPassword());
                            database.insert("User", null, values);
                        } else {
                            Toast.makeText(LoginActivity.this, "账号或密码不符合要求", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "账号已存在", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (accountEdit.getText() != null && accountEdit.getText().length() >= 6 && passwordEdit.getText() != null && passwordEdit.getText().length() >= 8) {
                        Users users = new Users();
                        users.setAccount(accountEdit.getText().toString());
                        users.setPassword(passwordEdit.getText().toString());
                        ContentValues values = new ContentValues();
                        values.put("account", users.getAccount());
                        values.put("password", users.getPassword());
                        database.insert("User", null, values);
                    } else {
                        Toast.makeText(LoginActivity.this, "账号或密码不符合要求", Toast.LENGTH_SHORT).show();
                    }
                }
                cursor.close();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {//实现登录功能
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = mDatabaseHelper.getWritableDatabase();
                Cursor cursor = database.query("User", null, null, null, null, null, null);
                boolean flag = false;
                if (cursor.moveToFirst()) {
                    do {
                        String account = cursor.getString(cursor.getColumnIndex("account"));
                        String password = cursor.getString(cursor.getColumnIndex("password"));
                        if (account.equals(accountEdit.getText().toString()) && password.equals(passwordEdit.getText().toString())) {
                            flag = true;
                        }
                    } while (cursor.moveToNext());
                    if (flag) {
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("account",accountEdit.getText().toString());
                        isLogin = true;
                        //实现记住密码功能
                        mEditor = mSharedPreferences.edit();
                        if (remember_password.isChecked()){
                            mEditor.putBoolean("remember_password",true);
                            mEditor.putString("account",accountEdit.getText().toString());
                            mEditor.putString("password",passwordEdit.getText().toString());
                        }else {
                            mEditor.clear();
                        }
                        mEditor.apply();
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "账号不存在或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("is_login",isLogin);
    }
}
