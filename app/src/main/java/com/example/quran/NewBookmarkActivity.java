package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewBookmarkActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Ayat> ayatArrayList = new QuranDAO(getApplicationContext()).getAyatByBookmark();
        if(ayatArrayList.isEmpty()){
            TextView textView = findViewById(R.id.emptyText);
            textView.setText("No bookmarks saved!");
            recyclerView.setAdapter(new RecyclerAdapterBookmark(getApplicationContext(),new ArrayList<>(),0,0));
        }
        else{
            Intent intent = getIntent();
            int translateEng = intent.getIntExtra("translateEng",0);
            int translateUrdu = intent.getIntExtra("translateUrdu",0);
            recyclerView.setAdapter(new RecyclerAdapterBookmark(getApplicationContext(),ayatArrayList,translateEng,translateUrdu));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bookmark);

        QuranDAO quranDAO = new QuranDAO(this);
        Intent intent = getIntent();
        int translateEng = intent.getIntExtra("translateEng",0);
        int translateUrdu = intent.getIntExtra("translateUrdu",0);
        List<Ayat> array = quranDAO.getAyatByBookmark();

        recyclerView = findViewById(R.id.bookmark_recycler_view);
        RecyclerAdapterBookmark recyclerAdapter = new RecyclerAdapterBookmark(NewBookmarkActivity.this, array, translateEng, translateUrdu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewBookmarkActivity.this);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}