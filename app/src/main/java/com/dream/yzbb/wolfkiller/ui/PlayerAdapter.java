package com.dream.yzbb.wolfkiller.ui;

import android.app.Activity;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.entity.Player;

import java.util.List;

/**
 * Created by liuzhaofeng on 2016/3/3.
 */
public class PlayerAdapter extends BaseAdapter {
    private List<Player> mPlayerList;
    private LayoutInflater mLayoutInflater;
    /*package*/ PlayerAdapter(Activity activity, List<Player> playerList) {
        mPlayerList = playerList;
        mLayoutInflater = LayoutInflater.from(activity);
    }
    @Override
    public int getCount() {
        return mPlayerList.size();
    }

    @Override
    public Player getItem(int position) {
        return mPlayerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getPlayID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_main_grid_player, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.player_no);
        tv.setText(String.valueOf(position)+"\n"+getItem(position).getRole().getName());
        return convertView;
    }
}
