package com.example.edgepanelofgalaxy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    private LinearLayout leftLayout;
    private LinearLayout rightLayout;

    Context mContext;
    ArrayList<ApplicationInfo> mAllAppsListWithoutSystemApps;
    PackageManager mPackageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mPackageManager = getPackageManager();
        mAllAppsListWithoutSystemApps = getAllAppsListWithoutSystemApp();

        leftLayout = findViewById(R.id.left_layout);
        rightLayout = findViewById(R.id.right_layout);

        CustomDragListener listener = new CustomDragListener();
        leftLayout.setOnDragListener(listener);
        rightLayout.setOnDragListener(listener);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rLayoutManager);

        MyAdapter adapter = new MyAdapter(this, mAllAppsListWithoutSystemApps, mPackageManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onLongClick(View v) {
        v.startDragAndDrop(null, new View.DragShadowBuilder(v), (Object) v, 0);
        return true;
    }

    List<ApplicationInfo> getAllAppsList() {
        return mPackageManager.getInstalledApplications(PackageManager.GET_META_DATA);
    }

    ArrayList<ApplicationInfo> getAllAppsListWithoutSystemApp() {
        ArrayList<ApplicationInfo> applicationInfoList = new ArrayList<>();
        for (ApplicationInfo info : getAllAppsList()) {
            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) {
                continue;
            }
            applicationInfoList.add(info);
        }
        return applicationInfoList;
    }
}