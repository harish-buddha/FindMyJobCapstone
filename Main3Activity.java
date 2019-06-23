package com.example.harish.findmyjobcapstone;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harish.findmyjobcapstone.SaveJobsData.JobViewModel;
import com.example.harish.findmyjobcapstone.SaveJobsData.SaveJobData;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Main3Activity extends AppCompatActivity {

    public static final String id = "id";
    public static final String company = "company";
    public static final String company_logo = "company_logo";
    public static final String company_url = "company_url";
    public static final String created_at = "created_at";
    public static final String description = "description";
    public static final String how_to_apply = "how_to_apply";
    public static final String location = "location";
    public static final String Type = "Type";
    public static final String Url = "Url";
    public static final String title = "title";
    LikeButton likeButton;
    ImageView imageView;
    String jobid;
    String description_html, How_to_apply_html;
    JobViewModel viewModel;
    TextView tv1, tv2, tv3, tv4, tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        likeButton = findViewById(R.id.star_button);
        tv1 = findViewById(R.id.title);
        imageView = findViewById(R.id.company_img);
        tv2 = findViewById(R.id.description);
        tv3 = findViewById(R.id.how_to_apply);
        tv4 = findViewById(R.id.location_tv);
        tv5 = findViewById(R.id.created_at);
        jobid = getIntent().getStringExtra(id);
        viewModel = ViewModelProviders.of(this).get(JobViewModel.class);
        description_html = Html.fromHtml(getIntent().getStringExtra(description)).toString();
        How_to_apply_html = Html.fromHtml(getIntent().getStringExtra(how_to_apply)).toString();
        tv1.setText(getIntent().getStringExtra(title));
        Picasso.get().load(getIntent().getStringExtra(company_logo)).into(imageView);
        tv2.setText(description_html);
        tv3.setText(How_to_apply_html);
        tv4.setText(getIntent().getStringExtra(location));
        tv5.setText(getIntent().getStringExtra(created_at));
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {


                SaveJobData saveJobData = new SaveJobData();
                saveJobData.setId(jobid);
                saveJobData.setHow_to_apply(getIntent().getStringExtra(how_to_apply));
                saveJobData.setTitle(getIntent().getStringExtra(title));
                saveJobData.setCompany(getIntent().getStringExtra(company));
                saveJobData.setCreated_at(getIntent().getStringExtra(created_at));
                saveJobData.setCompany_logo(getIntent().getStringExtra(company_logo));
                saveJobData.setLocation(getIntent().getStringExtra(location));
                saveJobData.setDescription(getIntent().getStringExtra(description));
                viewModel.insert(saveJobData);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                SaveJobData saveJobData = new SaveJobData();
                saveJobData.setId(jobid);
                saveJobData.setTitle(getIntent().getStringExtra(title));
                saveJobData.setHow_to_apply(getIntent().getStringExtra(how_to_apply));
                saveJobData.setCompany(getIntent().getStringExtra(company));
                saveJobData.setCreated_at(getIntent().getStringExtra(created_at));
                saveJobData.setCompany_logo(getIntent().getStringExtra(company_logo));
                saveJobData.setLocation(getIntent().getStringExtra(location));
                saveJobData.setDescription(getIntent().getStringExtra(description));
                viewModel.deleteData(saveJobData);

            }
        });

        checkfavourite();
    }

    private void checkfavourite() {


        viewModel.getListLiveData().observe(this, new Observer<List<SaveJobData>>() {
            @Override
            public void onChanged(@Nullable List<SaveJobData> saveJobData) {

                SaveJobData saveJobData1 = viewModel.findJobData(jobid);
                if (saveJobData1 != null) {
                    likeButton.setLiked(true);
                } else {
                    likeButton.setLiked(false);
                }

            }
        });


    }


}
