package com.example.edgepanelofgalaxy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    Context mContext;
    List<ApplicationInfo> appList;
    PackageManager mPackageManager;


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.icon);
            textView = itemView.findViewById(R.id.app_name);
        }
    }

    public MyAdapter(Context context,List<ApplicationInfo> localDataSet, PackageManager packageManager) {
        mContext = context;
        appList = localDataSet;
        mPackageManager = packageManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(appList.get(position).loadIcon(mPackageManager));
        holder.textView.setText(appList.get(position).loadLabel(mPackageManager));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ComponentName componentName;
//                componentName = new ComponentName(appList.get(position).packageName,appList.get(position).className);
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                int launchFlag = Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED;
//                intent.setFlags(launchFlag);
//                intent.setComponent(componentName);
//                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

}
