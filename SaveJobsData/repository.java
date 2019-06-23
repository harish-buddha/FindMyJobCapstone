package com.example.harish.findmyjobcapstone.SaveJobsData;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class repository {

    private MyDao jobDao;
    private LiveData<List<SaveJobData>> saveJobData;

    public repository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        jobDao = database.myDao();
        saveJobData = jobDao.getall();
    }

    public SaveJobData findJob(String id) {
        SaveJobData jobData = jobDao.search(id);
        return jobData;
    }

    public LiveData<List<SaveJobData>> listLiveData() {
        return saveJobData;
    }

    public void insertData(SaveJobData saveJobData) {
        new MyTask(jobDao).execute(saveJobData);
    }

    public void deleteData(SaveJobData saveJobData) {
        new MyDeleteTask(jobDao).execute(saveJobData);
    }

    public class MyTask extends AsyncTask<SaveJobData, Void, Void> {

        public MyDao mDao;

        public MyTask(MyDao jobDao) {
            mDao = jobDao;
        }

        @Override
        protected Void doInBackground(SaveJobData... saveJobData) {
            mDao.insert(saveJobData[0]);
            return null;
        }
    }

    public class MyDeleteTask extends AsyncTask<SaveJobData, Void, Void> {

        public MyDao dao;

        public MyDeleteTask(MyDao jobDao) {
            dao = jobDao;
        }

        @Override
        protected Void doInBackground(SaveJobData... saveJobData) {
            dao.delete(saveJobData[0]);
            return null;
        }
    }

}
