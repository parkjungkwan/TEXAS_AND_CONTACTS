package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.function.Supplier;

public class MemberList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_list);
        final Context _this = MemberList.this;
        final ItemList query = new ItemList(_this);
        ListView memberList = findViewById(R.id.memberList);
        memberList.setAdapter(
                new MemberAdapter(_this, new Supplier<ArrayList<Main.Member>>() {
            @Override
            public ArrayList<Main.Member> get() {
                return query.get();
            }
        }.get()));

    }
    private class MemberListQuery extends Main.QueryFactory{
        SQLiteOpenHelper helper;

        public MemberListQuery(Context _this) {
            super(_this);
            helper = new Main.SQLiteHelper(_this);
        }

        @Override
        public SQLiteDatabase getDatabase() {
            return helper.getReadableDatabase();
        }
    }
    private class ItemList extends MemberListQuery{

        public ItemList(Context _this) {
            super(_this);
        }
        public ArrayList<Main.Member> get(){
            ArrayList<Main.Member> list = new ArrayList<>();
            Cursor cursor = this.getDatabase().rawQuery(
                    " SELECT * FROM MEMBERS ", null);
            Main.Member member = null;
            if(cursor != null){
                member = new Main.Member();
                member.seq = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Main.SEQ)));
                member.name = cursor.getString(cursor.getColumnIndex(Main.NAME));
                member.passwd = cursor.getString(cursor.getColumnIndex(Main.PASSWD));
                member.email = cursor.getString(cursor.getColumnIndex(Main.EMAIL));
                member.phone = cursor.getString(cursor.getColumnIndex(Main.PHONE));
                member.addr = cursor.getString(cursor.getColumnIndex(Main.ADDR));
                member.photo = cursor.getString(cursor.getColumnIndex(Main.PHOTO));
                list.add(member);
            }else{
                Toast.makeText(_this, "등록된 친구가 없음", Toast.LENGTH_LONG).show();
            }
            return list;
        }

    }
    private class MemberAdapter extends BaseAdapter{

        ArrayList<Main.Member> list;
        LayoutInflater inflater;
        Context _this;

        public MemberAdapter(Context _this, ArrayList<Main.Member> list) {
            this.list = list;
            this._this = _this;
            this.inflater = LayoutInflater.from(_this);

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i, View v, ViewGroup g) {
            ViewHolder holder;
            if(v == null){
                v = inflater.inflate(R.layout.member_item, null);
                holder = new ViewHolder();
                holder.photo = v.findViewById(R.id.profile);
                holder.name = v.findViewById(R.id.name);
                holder.phone = v.findViewById(R.id.phone);
                v.setTag(holder);
            }else{
                holder = (ViewHolder)v.getTag();
            }

            holder.name.setText(list.get(i).name);
            holder.phone.setText(list.get(i).phone);
            return v;
        }
    }
    static class ViewHolder{
        ImageView photo;
        TextView name, phone;
    }
}











