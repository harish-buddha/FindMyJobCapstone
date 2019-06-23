package com.example.harish.findmyjobcapstone;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    List<MyJobs> list;

    public MyAdapter(Main2Activity main2Activity, List<MyJobs> list) {
        this.context = main2Activity;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getCompany_logo()).resize(100, 100).placeholder(R.mipmap.jobshiring).into(holder.imageView);
        holder.tv1.setText(list.get(position).getTitle());
        holder.tv2.setText(list.get(position).getCreated_at());
        holder.tv3.setText(list.get(position).getLocation());

    }

    @Override
    public int getItemCount() {
        return list.size();
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
            String title=list.get(pos).getTitle();  
            String company=list.get(pos).getCompany();
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared), MODE_PRIVATE);
            Intent intent = new Intent(context, Main3Activity.class);
            intent.putExtra(Main3Activity.id, list.get(pos).getId());
            intent.putExtra(Main3Activity.company, list.get(pos).getCompany());
            intent.putExtra(Main3Activity.company_logo, list.get(pos).getCompany_logo());
            intent.putExtra(Main3Activity.company_url, list.get(pos).getCompanyurl());
            intent.putExtra(Main3Activity.created_at, list.get(pos).getCreated_at());
            intent.putExtra(Main3Activity.description, list.get(pos).getDescription());
            intent.putExtra(Main3Activity.how_to_apply, list.get(pos).getHow_to_apply());
            intent.putExtra(Main3Activity.location, list.get(pos).getLocation());
            intent.putExtra(Main3Activity.Type, list.get(pos).getType());
            intent.putExtra(Main3Activity.Url, list.get(pos).getUrl());
            intent.putExtra(Main3Activity.title, list.get(pos).getTitle());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putString(Main3Activity.title,title );
            editor.putString(Main3Activity.company,company);
            editor.apply();

            Intent intent1 = new Intent(context, JobWidget.class);
            intent1.setAction(context.getString(R.string.widgetaction));
            int widget[] = AppWidgetManager.getInstance(context.getApplicationContext()).getAppWidgetIds(new ComponentName(context.getApplicationContext(), JobWidget.class));
            intent1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, widget);
            context.sendBroadcast(intent1);


            context.startActivity(intent);


        }
    }
}
