package com.example.demoapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demoapp.adapters.GameAdapter;
import com.example.demoapp.models.Game;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Game> dataList = new ArrayList<>();
    GameAdapter gameAdapter;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.afo_listview);

//        //fake data
//        dataList.add(new Game(R.drawable.game01, "Pizza 01", "Ok Ngon 01", 100000));
//        dataList.add(new Game(R.drawable.game02, "Pizza 02", "Ok Ngon 02", 120000));
//        dataList.add(new Game(R.drawable.game03, "Pizza 03", "Ok Ngon 03", 160000));

        //create adapter
        gameAdapter = new GameAdapter(this, dataList);
        listView.setAdapter(gameAdapter);

        //dang ky context menu
        registerForContextMenu(listView);

        //Tai dc tai nguyen tren mang
        Request request = new Request.Builder()
                .url(Config.URL_GAME)
                .build();
        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //code => tai thanh cong
                if (response.isSuccessful()) {
                    final String json = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            displayListView(json);
                        }
                    });
//                    Log.d(MainActivity.class.getName(), json);
//                    Toast.makeText(MainActivity.this, json, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void displayListView(String json) {
        //parse json => listview
        Gson gson = new Gson();
        TypeToken<List<Game>> typeToken = new TypeToken<List<Game>>() {};
        dataList = gson.fromJson(json, typeToken.getType());

        gameAdapter.setDataList(dataList);

        gameAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_content, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        switch (item.getItemId()) {
            case R.id.menu_edit_item:
                //Them item moi
                this.currentIndex = index;
                showDialogAdd();
                return true;
            case R.id.menu_delete_item:
                dataList.remove(index);
                gameAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_add_new_item:
                //Them item moi
                showDialogAdd();
                return true;
            case R.id.menu_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_game, null);

        final EditText edTitle = view.findViewById(R.id.df_title);
        final EditText edContent = view.findViewById(R.id.df_content);
        final EditText edPrice = view.findViewById(R.id.df_price);
        if(currentIndex >= 0) {
            edTitle.setText(dataList.get(currentIndex).getTitle());
            edContent.setText(dataList.get(currentIndex).getContent());
            edPrice.setText(String.valueOf(dataList.get(currentIndex).getPrice()));
        }

        builder.setView(view);
        builder.setTitle("Add/Update Item")
                .setPositiveButton("Save Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = edTitle.getText().toString();
                        String content = edContent.getText().toString();
                        float price = Float.parseFloat(edPrice.getText().toString());

                        if(currentIndex >= 0) {
                            dataList.get(currentIndex).setTitle(title);
                            dataList.get(currentIndex).setContent(content);
                            dataList.get(currentIndex).setPrice(price);
                            currentIndex = -1;
                        } else {
                            Game game = new Game(R.drawable.game01, title, content, price);
                            dataList.add(game);
                        }

                        gameAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }
}