package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class GameInfoDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_info);
        Intent intent = getIntent();
        GameInfo details = (GameInfo) intent.getSerializableExtra("game_info");
        TextView mnametv = findViewById(R.id.tvNameClick);
        TextView mpricetv = findViewById(R.id.tvPriceClick);
        TextView mcontenttv = findViewById(R.id.tvContentClick);
        ImageView imageView = findViewById(R.id.imgClick);
        mnametv.setText(details.getName());
        mpricetv.setText(details.getPrice());
        mcontenttv.setText(details.getContent());
        Picasso.get().load(details.getThumbnail()).into(imageView);
    }
}
