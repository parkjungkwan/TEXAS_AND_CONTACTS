package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Context _this = Main.this;
        findViewById(R.id.next_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(_this, Login.class));
                    }
                });
    }
    static class Member{int seq; String name, passwd, email, phone, addr, photo;}
    static String DBNAME = "contacts.db";
    static String MEMBERS = "MEMBERS";
    static String SEQ = "SEQ";
    static String NAME = "NAME";
    static String PASSWD = "PASSWD";
    static String EMAIL = "EMAIL";
    static String PHONE = "PHONE";
    static String ADDR = "ADDR";
    static String PHOTO = "PHOTO";
    static abstract class QueryFactory{
        Context _this;
        public QueryFactory(Context _this){this._this = _this;}
        public abstract SQLiteDatabase getDatabase();
    }
    static class SQLiteHelper extends SQLiteOpenHelper{

        public SQLiteHelper(Context context){
            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
