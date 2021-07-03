package com.example.edgepanelofgalaxy;

import androidx.appcompat.app.AppCompatActivity;

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

    int[] layoutIds = {R.id.item1,R.id.item2,R.id.item3,R.id.item4,R.id.item5,
            R.id.item6,R.id.item7,R.id.item8,R.id.item9,R.id.item10};

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

        for (int i = 0; i < layoutIds.length; i++) {
            LinearLayout linearLayout = findViewById(layoutIds[i]);
            linearLayout.setOnLongClickListener(this);
            ImageView appIcon = linearLayout.findViewById(R.id.icon);
            TextView appName = linearLayout.findViewById(R.id.app_name);
            appIcon.setImageDrawable(mAllAppsListWithoutSystemApps.get(i).loadIcon(mPackageManager));
            appName.setText(mAllAppsListWithoutSystemApps.get(i).loadLabel(mPackageManager));
        }

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