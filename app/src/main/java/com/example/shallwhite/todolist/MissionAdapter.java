package com.example.shallwhite.todolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2017/2/26.
 */

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.ViewHolder> {
    //在mission的recyclerView中显示
    private Context mContext;
    private List<Mission> mMissionList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        TextView date;
        public ViewHolder(View view){
            super(view);
            text = (TextView)view.findViewById(R.id.mission_text);
            date = (TextView)view.findViewById(R.id.mission_date);
        }
    }

    public MissionAdapter(List<Mission> missionList){
        mMissionList = missionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.mission_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mission mission = mMissionList.get(position);
        holder.text.setText(mission.getText());
        holder.date.setText(mission.getDate());
    }

    @Override
    public int getItemCount() {
        return mMissionList.size();
    }
}
