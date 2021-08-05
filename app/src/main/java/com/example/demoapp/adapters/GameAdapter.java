package com.example.demoapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoapp.R;
import com.example.demoapp.models.Game;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GameAdapter extends BaseAdapter {
    Activity activity;
    List<Game> dataList;

    public GameAdapter(Activity activity, List<Game> dataList) {
        this.activity = activity;
        this.dataList = dataList;
    }

    public List<Game> getDataList() {
        return dataList;
    }

    public void setDataList(List<Game> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.item_game, null);

        ImageView thumbnail = view.findViewById(R.id.ifo_thumbnail);
        TextView title = view.findViewById(R.id.ifo_title);
        TextView content = view.findViewById(R.id.ifo_content);
        TextView price = view.findViewById(R.id.ifo_price);

        Game game = dataList.get(position);

        if(game.getThumbnail() == null || game.getThumbnail().isEmpty()) {
            thumbnail.setImageResource(game.getResId());
        } else {
            //Hien thi bang link hinh anh
            Picasso.get()
                    .load(game.getThumbnail())
                    .into(thumbnail);
        }

        title.setText(game.getTitle());
        content.setText(game.getContent());
        price.setText(String.valueOf(game.getPrice()));

        return view;
    }
}
