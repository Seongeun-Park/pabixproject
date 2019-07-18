package com.memo.pcw69.pabixreproject.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.memo.pcw69.pabixreproject.data.Word;

@Database(entities = {Word.class},version=1)
public abstract class WordRoomDatabase extends RoomDatabase {

    //데이터베이스와 연결되는 DAO
    //DAO는 abstract로 "getter"을 제공
    public abstract WordDao wordDao();

    //singleton pattern, room database는 한개만 존재
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if(INSTANCE ==null) {
            //동시에 2개의 INSTANCE가 생성되는 것을 막기위한 synchronized
            synchronized (WordRoomDatabase.class) {
                if(INSTANCE==null) {
                    //데이터 베이스 생성부분
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class,"word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}