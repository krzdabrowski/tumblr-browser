package com.example.trubul.tumblrbrowser;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by krzysiek
 * On 4/9/18.
 */

class GestureListener extends RecyclerView.SimpleOnItemTouchListener {
    private static final String TAG = "RecyclerItemClickListen";
    private final OnRecyclerClickListener mListener;
    private final GestureDetectorCompat mGestureDetector;

    interface OnRecyclerClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }


    public GestureListener(Context context, final RecyclerView recyclerView, OnRecyclerClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                if (!MainActivity.flagInit) {
                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());  // check and return which View was under coordinates(X,Y)

                    if (childView != null && mListener != null) {
                        mListener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
                    }

                    return true;
                }

                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

                if (!MainActivity.flagInit) {
                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());

                    if (childView != null && mListener != null) {
                        mListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
                    }
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if(mGestureDetector != null) {
            boolean result = mGestureDetector.onTouchEvent(e);
            return result;
        } else {
            return false;
        }
    }

}