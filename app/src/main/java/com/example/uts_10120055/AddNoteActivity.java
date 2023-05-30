package com.example.uts_10120055;
//10120055
//Abyan Dhiya Ulhaq
//IF-2

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    EditText title, details;
    Button btnTambah;
    String todayDate, currentTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_info );

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_note:
                    return true;
                case R.id.bottom_info:
                    startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        getSupportActionBar().setTitle("Tambahkan Catatan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.addNote);
        details = findViewById(R.id.detailNote);
        btnTambah = findViewById(R.id.btnTambah);

        calendar = Calendar.getInstance();
        todayDate = calendar.get(Calendar.YEAR)+"/"+calendar.get(calendar.MONTH)+"/"+calendar.get(calendar.DAY_OF_MONTH);
        currentTime = pad(calendar.get(Calendar.HOUR))+"/" +pad(calendar.get(Calendar.MINUTE));
        Log.d("Calendar", "Date and Time" + todayDate+" and " +currentTime);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteModel noteModel = new NoteModel(title.getText().toString(),details.getText().toString(),todayDate,currentTime);
                NoteDatabase db = new NoteDatabase(AddNoteActivity.this);
                db.AddNote(noteModel);

                Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Catatan Disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String pad(int i) {
        if (i < 10)
            return "0" + i;
        return String.valueOf(i);
    }
}
