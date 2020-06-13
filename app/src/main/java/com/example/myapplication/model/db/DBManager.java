package com.example.myapplication.model.db;

import android.content.Context;

import com.example.myapplication.model.dao.ContactTableDAO;
import com.example.myapplication.model.dao.InviteTableDAO;
import com.example.myapplication.model.dao.pyqDAO;

public class DBManager {
    private final DBHelper dbHelper;

    private final ContactTableDAO contactTableDAO;
    private final InviteTableDAO inviteTableDAO;
    private final pyqDAO pyqTableDao;
    public DBManager(Context context,String name) {
        dbHelper=new DBHelper(context,name);

        contactTableDAO = new ContactTableDAO(dbHelper);
        inviteTableDAO = new InviteTableDAO(dbHelper);
        //获得pyqtab的操作类
        pyqTableDao = new pyqDAO(dbHelper);
    }

    public ContactTableDAO getContactTableDAO() {
        return contactTableDAO;
    }

    public InviteTableDAO getInviteTableDAO() {
        return inviteTableDAO;
    }

    public pyqDAO getPyqTableDao(){
        return pyqTableDao;
    }
    public void close() {
        dbHelper.close();
    }
}
