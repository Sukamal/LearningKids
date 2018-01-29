package com.suku.learningkids.features.home;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class RecyclerSpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public RecyclerSpacesItemDecoration(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(space,space,space,space);
    }
}
