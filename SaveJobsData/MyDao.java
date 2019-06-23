package com.example.harish.findmyjobcapstone.SaveJobsData;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    @Query("select * from  savedjobs")
    LiveData<List<SaveJobData>> getall();

    @Insert
    void insert(SaveJobData jobsdata);

    @Delete
    void delete(SaveJobData jobsdata);

    @Query("select * from savedjobs where id == :id")
    SaveJobData search(String id);


}
