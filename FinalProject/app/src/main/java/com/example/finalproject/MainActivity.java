package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ConfessionAdapter.OnItemClicked {


    AppDatabase db;
    ConfessionAdapter confessionAdapter;
    public static List<Confession> confessions;
    RecyclerView recyclerViewShowAllConfession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        recyclerViewShowAllConfession = findViewById(R.id.rclviewShowConfessions_id);
        recyclerViewShowAllConfession.setLayoutManager(new LinearLayoutManager((this)));

        final Button btn_Add = findViewById(R.id.btn_addChoice);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence features[] = new CharSequence[] {"Add Text", "Add Photos"};
                showAlertAddChoices("Options", features);

            }
        });
    }


    private void showAlertAddChoices(String title, CharSequence features[]) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Lam chi do
                    }
                })
                .setItems(features, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            moveToAddConfessionScreen();
                        } else if (which == 1) {
                            Toast.makeText(MainActivity.this,"You have chose to add a photo",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .show();
    }


    private void moveToAddConfessionScreen() {
        Intent intent = new Intent(MainActivity.this, AddTextActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllAndShowTask();
    }

    public void getAllAndShowTask() {
        new AsyncTask<Void, Void, List<Confession>>() {
            @Override
            protected List<Confession> doInBackground(Void... voids) {
                confessions = db.confessionDao().getAll();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        confessionAdapter = new ConfessionAdapter(this, confessions);
                        confessionAdapter.setOnClickAtItem(MainActivity.this);
                        recyclerViewShowAllConfession.setAdapter(confessionAdapter);
                        Toast.makeText(MainActivity.this, "Number of confession " + confessions.size(), Toast.LENGTH_SHORT).show();
                    }
                });
                return null;
            }
        }.execute();
    }

    private void moveToUpdateConfessionScreen(Confession confession) {
        Intent intent = new Intent(MainActivity.this, UpdateTextActivity.class);
        intent.putExtra("id", confession.getId());
        intent.putExtra("title", confession.getTitle());
        intent.putExtra("content", confession.getContent());
        startActivity(intent);
    }

    @Override
    public void onClickItemUpdate(int position) {
        moveToUpdateConfessionScreen(confessions.get(position));
    }

    public void onClickItemDelete(final int position) {
        Log.i("TAG", "clicked at " + position);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.confessionDao().delete(confessions.get(position));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                confessionAdapter.confession.remove(position);
                confessionAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Item was deleted", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    @Override
    public void onClickItem(int position) {
        moveToDetailConfessionScreen(confessions.get(position));
    }

    private void moveToDetailConfessionScreen(Confession confession) {
        Intent intent = new Intent(MainActivity.this, DetailConfessionActivity.class);
        intent.putExtra("id", confession.getId());
        intent.putExtra("title", confession.getTitle());
        intent.putExtra("content", confession.getContent());
        intent.putExtra("date", confession.getDate());
        startActivity(intent);
    }
}
