package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MemberUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_update);
        final Context _this = MemberUpdate.this;
        String[] arr = this.getIntent()
                .getStringExtra("spec")
                .split(",");
        /*
        * member.seq,member.name,
                                member.email,member.phone,
                                member.addr,member.photo
        * */
        ImageView profile = findViewById(R.id.profile);
        profile.setImageDrawable(
                getResources()
                        .getDrawable(
                                getResources()
                                        .getIdentifier(
                                                _this.getPackageName()+":drawable/"+arr[5],
                                                null,
                                                null
                                        )
                        )
        );
        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.changeEmail);
        TextView phone = findViewById(R.id.changePhone);
        TextView addr = findViewById(R.id.changeAddress);
        name.setText(arr[1]);
        email.setText(arr[2]);
        phone.setText(arr[3]);
        addr.setText(arr[4]);
    }
    private class MemberUpdateQuery extends Main.QueryFactory{
        SQLiteOpenHelper helper;
        public MemberUpdateQuery(Context _this) {
            super(_this);
            helper = new Main.SQLiteHelper(_this);
        }

        @Override
        public SQLiteDatabase getDatabase() {
            return helper.getWritableDatabase();
        }
    }
    private class ItemUpdate extends MemberUpdateQuery {
        String name, email, phone, addr, seq;
        public ItemUpdate(Context _this) {
            super(_this);
        }
        public void run(){
            String sql = String.format(
                    " UPDATE %s\n" +
                    "     SET %s = '%s',\n" +
                    "         %s = '%s',\n" +
                    "         %s = '%s',\n" +
                    "         %s = '%s'\n" +
                    "     WHERE %s LIKE '%s'",
                    Main.MEMBERS, Main.NAME, name,
                    Main.EMAIL, email,
                    Main.PHONE, phone,
                    Main.ADDR, addr, Main.SEQ, seq);
            getDatabase().execSQL(sql);

        }
    }

}
