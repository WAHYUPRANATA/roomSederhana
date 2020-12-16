package com.example.roomsederhana;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.WordDao();
        mAllWords = mWordDao.getAlphabetizeWords();
    }

    LiveData<List<Word>> getmAllWords() { return mAllWords; }

    void insert(final Word word) {
        WordRoomDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mWordDao.insert(word);
            }
        });
    }
}