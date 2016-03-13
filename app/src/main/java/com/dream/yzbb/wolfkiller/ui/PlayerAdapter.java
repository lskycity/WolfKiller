package com.dream.yzbb.wolfkiller.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.yzbb.wolfkiller.R;
import com.dream.yzbb.wolfkiller.entity.Player;
import com.dream.yzbb.wolfkiller.utils.ViewUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuzhaofeng on 2016/3/3.
 */
public class PlayerAdapter extends BaseAdapter {
    private final List<Player> mPlayerList;
    private final LayoutInflater mLayoutInflater;

    private int mSelectedCount = 0;
    private LinkedList<Integer> mSelectPosition = new LinkedList<>();

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

    public void setSelectedCount(int count) {
        mSelectedCount = count;
        mSelectPosition.clear();
        notifyDataSetChanged();
    }

    public Player[] getSelectPlayers() {
        int count = mSelectPosition.size();
        Player[] players = new Player[count];
        for(int i=0; i<count; i++) {
            players[i] = mPlayerList.get(mSelectPosition.get(i));
        }
        return players;
    }

    public void setSelectedPosition(int position) {
        if(mSelectPosition.contains(Integer.valueOf(position))) {
            mSelectPosition.remove(Integer.valueOf(position));
        } else {
            mSelectPosition.add(position);
        }

        if(mSelectPosition.size()>mSelectedCount) {
            mSelectPosition.removeFirst();
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_main_grid_player, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.player_no);
        tv.setText(getItem(position).getPlayID()+","+getItem(position).getRole().getName());
        tv.append(" "+getItem(position).getStatus());
        ImageView checked = (ImageView) convertView.findViewById(R.id.checked_image);
        ViewUtils.setVisible(checked, mSelectPosition.contains(Integer.valueOf(position)));
        return convertView;
    }
}
