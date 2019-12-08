package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DetailConfessionActivity extends AppCompatActivity {

    int id_update_confession;
    TextView tv_titleConfession;
    TextView tv_contentConfession;
    Button btn_update_Confession;
    TextView tv_createdDate;
    public static List<Confession> confessions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_confession);

        tv_titleConfession = findViewById(R.id.tv_detailTitleConfession);
        tv_contentConfession = findViewById(R.id.tv_detailContentConfession);
        tv_createdDate = findViewById(R.id.tv_createdDateConfession);
        btn_update_Confession = findViewById(R.id.btn_updateConfession);

        id_update_confession = getIntent().getIntExtra("id", 0);
        final String title_confession = getIntent().getStringExtra("title");
        final String content_confession = getIntent().getStringExtra("content");
        final String date_confession = getIntent().getStringExtra("date");

        tv_createdDate.setText("Chỉnh sửa lúc: " +date_confession);
        tv_titleConfession.setText(title_confession);
        tv_contentConfession.setText(content_confession);

        btn_update_Confession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailConfessionActivity.this, UpdateTextActivity.class);

                intent.putExtra("id", id_update_confession);
                intent.putExtra("title", title_confession);
                intent.putExtra("content",content_confession);
                startActivity(intent);
                finish();
            }
        });
    }


}
