package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateTextActivity extends AppCompatActivity {

    AppDatabase db;
    int id_update_confession;
    EditText edt_update_titleConfession;
    EditText edt_update_contentConfession;
    Button btn_update_Confession;
    Button btn_cancel_update_Confession;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String changed_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_text);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        edt_update_titleConfession = findViewById(R.id.edt_updateTitleConfession);
        edt_update_contentConfession = findViewById(R.id.edt_updateContentConfession);
        btn_update_Confession = findViewById(R.id.btn_saveUpdateConfession);
        btn_cancel_update_Confession = findViewById(R.id.btn_cancelUpdateAction);

        id_update_confession = getIntent().getIntExtra("id", 0);
        String title_confession = getIntent().getStringExtra("title");
        String content_confession = getIntent().getStringExtra("content");

        edt_update_titleConfession.setText(title_confession);
        edt_update_contentConfession.setText(content_confession);


        btn_update_Confession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChangesToDB();
            }
        });

        btn_cancel_update_Confession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void saveChangesToDB() {
        final String update_titleConfession = edt_update_titleConfession.getText().toString();
        final String update_contentConfession = edt_update_contentConfession.getText().toString();
        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        changed_date = simpledateformat.format(calander.getTime());

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Confession updated_confession = new Confession();
                updated_confession.setId(id_update_confession);
                updated_confession.setTitle(update_titleConfession);
                updated_confession.setContent(update_contentConfession);
                updated_confession.setDate(changed_date);

                db.confessionDao().updateOne(updated_confession);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                showSuccessDialog();
            }
        }.execute();
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Update successfully")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();
    }
}
