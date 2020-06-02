package com.e.bluetooth_chat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
    SQLite数据库打开助手DbHelper作为抽象类SQLiteOpenHelper的子类
    需要重写2个抽象方法onCreate()和onUpgrade()
*/
public class DbHelper extends SQLiteOpenHelper {

    public static final String TB_NAME = "friends";  //表名

    //构造方法：第1参数为上下文，第2参数库库名，第3参数为游标工厂，第4参数为版本
    DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);//创建或打开数据库
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //当表不存在时，创建表；第一字段为自增长类型
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TB_NAME + "( _id integer primary key autoincrement," +
                "name varchar," + "tel varcher"+ ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 执行SQL命令
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
    }
}
