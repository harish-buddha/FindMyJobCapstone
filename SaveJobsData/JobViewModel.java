package com.example.harish.findmyjobcapstone.SaveJobsData;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class JobViewModel extends AndroidViewModel {

    repository movieRepository;

    private LiveData<List<SaveJobData>> listLiveData;

    public JobViewModel(Application application) {
        super(application);
        movieRepository = new repository(application);
        listLiveData = movieRepository.listLiveData();
    }


    public SaveJobData findJobData(String id) {
        SaveJobData favMovieData = movieRepository.findJob(id);
        return favMovieData;
    }

    public LiveData<List<SaveJobData>> getListLiveData() {
        return listLiveData;
    }


    public void insert(SaveJobData saveJobData) {
        movieRepository.insertData(saveJobData);
    }

    public void deleteData(SaveJobData saveJobData) {
        movieRepository.deleteData(saveJobData);
    }

}
