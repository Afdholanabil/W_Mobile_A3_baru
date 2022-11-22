package com.example.recyclick.Koneksi;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemListener implements RecyclerView.OnItemTouchListener {
    private onItemClickListener mListener;

    public interface onItemClickListener{
        public void onItemClick(View view, int position);
    }

    GestureDetector mGestureDetector;

    public RecyclerItemListener(Context context, onItemClickListener listener){
        mListener = listener;
        mGestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
        });

    }
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView view, @NonNull MotionEvent e) {
        View childview = view.findChildViewUnder(e.getX(), e.getY());
        if(childview != null && mListener !=null && mGestureDetector.onTouchEvent(e)){
            mListener.onItemClick(childview, view.getChildAdapterPosition(childview));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
