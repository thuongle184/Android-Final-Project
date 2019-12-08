package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTextActivity extends AppCompatActivity {

    AppDatabase db;
    String confession_title;
    String confession_content;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String created_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        final Button btn_AddANewConfession = (Button) findViewById(R.id.btn_AddConfession);

        btn_AddANewConfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addConfession();
                finish();
            }
        });
    }

    public void addConfession() {
        final EditText ed_titleConfession =  findViewById(R.id.edt_titleConfession);
        final EditText ed_contentConfession =  findViewById(R.id.edt_contentConfession);

        confession_title= ed_titleConfession.getText().toString();
        confession_content= ed_contentConfession.getText().toString();
        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        created_date = simpledateformat.format(calander.getTime());


        Log.d("gfgdfg", confession_title);
        Log.d("ggg", confession_content);
        Log.d("ggdateg", created_date);


        if (confession_title.isEmpty() || confession_content.isEmpty()) {
            Toast.makeText(this, "Type anything", Toast.LENGTH_SHORT).show();
            return;
        }


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Confession newConfession = new Confession(created_date,confession_title,confession_content);
                Log.d("hfdfdjgfjh",newConfession.toString());
                db.confessionDao().insert(newConfession);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(AddTextActivity.this, confession_title + " has been added successfully", Toast.LENGTH_SHORT).show();

            }
        }.execute();
    }
}
