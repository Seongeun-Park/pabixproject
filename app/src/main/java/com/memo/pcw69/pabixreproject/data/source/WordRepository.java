package com.memo.pcw69.pabixreproject.data.source;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.memo.pcw69.pabixreproject.data.Word;
import com.memo.pcw69.pabixreproject.data.source.local.WordDao;
import com.memo.pcw69.pabixreproject.data.source.local.WordRoomDatabase;

import java.util.List;

public class WordRepository {

    //Dao의 멤버변수와 word를 넣을 list변수를 만들어준다.
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db=WordRoomDatabase.getDatabase(application);
        //RoomDatabase에 있는 Dao를 가져온다.
        mWordDao=db.wordDao();
        //Dao의 쿼리를 이용하여 저장되어있는 모든 word를 가져온다.
        mAllWords=mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    //word를 추가하는 함수
    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao=dao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }
}