package com.example.edgepanelofgalaxy;

import android.graphics.Color;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomDragListener implements View.OnDragListener {
    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                View dragView = (View) event.getLocalState();
                dragView.setBackgroundColor(Color.TRANSPARENT);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
        }
        return true;
    }
}
