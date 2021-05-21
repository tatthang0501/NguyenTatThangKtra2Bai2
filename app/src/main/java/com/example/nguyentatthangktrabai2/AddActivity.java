package com.example.nguyentatthangktrabai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nguyentatthangktrabai2.model.Course;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private EditText edtNameAdd;
    private TextView tvDateAdd;
    private Button btnGetDateAdd, btnAddAdd, btnBackAdd;
    private CheckBox cbAddActivate;
    private Spinner spMajor;
    private SQLiteHelper sqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        sqlHelper = new SQLiteHelper(AddActivity.this);
        btnAddAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course c = new Course();
                c.setName(edtNameAdd.getText().toString());
                c.setDate(tvDateAdd.getText().toString());
                c.setMajor(spMajor.getSelectedItem().toString());
                if(cbAddActivate.isChecked()){
                    c.setActivate(true);
                }
                else{
                    c.setActivate(false);
                }
                long count = sqlHelper.addCourse(c);
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnBackAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnGetDateAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvDateAdd.setText(dayOfMonth+"/"+(month+1)+"/"+(year));
                    }
                },y,m,d);
                dpd.show();
            }
        });
    }

    private void init() {
        edtNameAdd = findViewById(R.id.edtNameAdd);
        tvDateAdd = findViewById(R.id.tvDateAdd);
        btnGetDateAdd = findViewById(R.id.btnGetDateAdd);
        btnAddAdd = findViewById(R.id.btnAddAdd);
        btnBackAdd = findViewById(R.id.btnBackAdd);
        spMajor = findViewById(R.id.spMajorAdd);
        cbAddActivate = findViewById(R.id.cbAddActivate);
    }
}