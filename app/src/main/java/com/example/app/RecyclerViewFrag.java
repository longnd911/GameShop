package com.example.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFrag extends Fragment{

    List<GameInfo> gameInfoList;
    RecyclerView recyclerView;


    public static RecyclerViewFrag newInstance() {

        Bundle args = new Bundle();

        RecyclerViewFrag fragment = new RecyclerViewFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_frag,container,false);


        gameInfoList = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gameInfoList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String Name = snapshot.child("name").getValue().toString();
                    String Price = snapshot.child("price").getValue().toString();
                    String Content = snapshot.child("content").getValue().toString();
                    String Thumbnail = snapshot.child("thumbnail").getValue().toString();
                    GameInfo gameInfo = new GameInfo(Name,Price,Content,Thumbnail);
                    gameInfoList.add(gameInfo);
                }
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false);
                AdapterRecyclerview adapterRecyclerview = new AdapterRecyclerview(gameInfoList, new AdapterRecyclerview.ItemClickListener() {
                    @Override
                    public void onItemClick(GameInfo details) {
//                        String mname = mnametv.getText().toString();
//                        String mprice = mpricetv.getText().toString();
//                        String mcontent = mcontenttv.getText().toString();
//                        Drawable drawable = imageView.getDrawable();
//                        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                        Intent intent = new Intent(getContext(), GameInfoDetails.class);
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                        byte[]bytes = stream.toByteArray();
//                        intent.putExtra("name", mname);
//                        intent.putExtra("price", mprice);
//                        intent.putExtra("content", mcontent);
//                        intent.putExtra("thumbnail", bytes);
                        intent.putExtra("game_info", (Serializable) details);
                        startActivity(intent);
                    }
                });

                recyclerView = view.findViewById(R.id.lvRecyclerview);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapterRecyclerview);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return view;
    }

}
