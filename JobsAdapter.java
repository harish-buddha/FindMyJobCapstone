package com.example.harish.findmyjobcapstone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.harish.findmyjobcapstone.SaveJobsData.SaveJobData;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {
    Context context;
    List<SaveJobData> saveJobDataList;

    public JobsAdapter(Main2Activity main2Activity, List<SaveJobData> saveJobDataList) {
        this.context = main2Activity;
        this.saveJobDataList = saveJobDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.get().load(saveJobDataList.get(position).getCompany_logo()).resize(100, 100).into(holder.imageView);
        holder.tv1.setText(saveJobDataList.get(position).getTitle());
        holder.tv2.setText(saveJobDataList.get(position).getCreated_at());
        holder.tv3.setText(saveJobDataList.get(position).getLocation());

    }

    @Override
    public int getItemCount() {
        return saveJobDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView imageView;
        TextView tv1, tv2, tv3;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            tv1 = itemView.findViewById(R.id.jobtitle);
            tv2 = itemView.findViewById(R.id.date_created);
            tv3 = itemView.findViewById(R.id.location);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Intent intent = new Intent(context, Main3Activity.class);
            intent.putExtra(Main3Activity.id, saveJobDataList.get(pos).getId());
            intent.putExtra(Main3Activity.company, saveJobDataList.get(pos).getCompany());
            intent.putExtra(Main3Activity.company_logo, saveJobDataList.get(pos).getCompany_logo());
            intent.putExtra(Main3Activity.company_url, saveJobDataList.get(pos).getCompanyurl());
            intent.putExtra(Main3Activity.created_at, saveJobDataList.get(pos).getCreated_at());
            intent.putExtra(Main3Activity.description, saveJobDataList.get(pos).getDescription());
            intent.putExtra(Main3Activity.how_to_apply, saveJobDataList.get(pos).getHow_to_apply());
            intent.putExtra(Main3Activity.location, saveJobDataList.get(pos).getLocation());
            intent.putExtra(Main3Activity.Type, saveJobDataList.get(pos).getType());
            intent.putExtra(Main3Activity.Url, saveJobDataList.get(pos).getUrl());
            intent.putExtra(Main3Activity.title, saveJobDataList.get(pos).getTitle());
            context.startActivity(intent);
        }
    }
}
