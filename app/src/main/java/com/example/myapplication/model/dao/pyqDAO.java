package com.example.myapplication.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.model.bean.pyqInfo;
import com.example.myapplication.model.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class pyqDAO {

    private DBHelper mHelper;
    public pyqDAO(DBHelper dbHelper) {
        mHelper=dbHelper;
    }


    //添加一条朋友圈
    public void addpyq(pyqInfo pyqinfo){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(pyqTable.COL_NAME,pyqinfo.getName());
        values.put(pyqTable.COL_MASSAGE,pyqinfo.getMessage());
        db.insert(pyqTable.TAB_NAME,null,values);
    }
    //查看朋友圈
    public List<pyqInfo> getpyqs(){
        SQLiteDatabase db=mHelper.getReadableDatabase();
        String sql= "select * from " +pyqTable.TAB_NAME;
        Cursor cursor=db.rawQuery(sql,null);

        List<pyqInfo> pyqsInfo=new ArrayList<>();
        while(cursor.moveToNext()){
            pyqInfo pyqInfo=new pyqInfo( );
            String name=new String();
            String message=new String();
            name=cursor.getString(cursor.getColumnIndex(pyqTable.COL_NAME));
            message=cursor.getString(cursor.getColumnIndex(pyqTable.COL_MASSAGE));
            pyqInfo.setName(name);
            pyqInfo.setMessage(message);
            pyqsInfo.add(pyqInfo);
        }
        cursor.close();
        return pyqsInfo;
    }

}
