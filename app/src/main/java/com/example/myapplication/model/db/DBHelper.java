package com.example.myapplication.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.bean.InviterInfo;
import com.example.myapplication.model.dao.ContactTable;
import com.example.myapplication.model.dao.InviteTable;
import com.example.myapplication.model.dao.pyqTable;

//联系人和邀请信息+朋友圈
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context,String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建联系人的表
        db.execSQL(ContactTable.CREATE_TAB);
        //创建邀请信息
        db.execSQL(InviteTable.CREATE_TAB);
        //创建朋友圈的表
        db.execSQL(pyqTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
