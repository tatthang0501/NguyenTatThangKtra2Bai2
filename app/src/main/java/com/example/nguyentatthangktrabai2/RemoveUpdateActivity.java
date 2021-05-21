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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nguyentatthangktrabai2.model.Course;

import java.util.Calendar;

public class RemoveUpdateActivity extends AppCompatActivity {
    private TextView tvId, tvDate;
    private Spinner spMajor;
    private EditText edtName;
    private CheckBox cbActivate;
    private Button btnGetDate, btnUpdate, btnDelete, btnBack;
    private SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_update);
        sqLiteHelper = new SQLiteHelper(this);
        init();
        Intent i = getIntent();
        Course c = (Course) i.getSerializableExtra("course");
        tvId.setText(c.getId()+"");
        tvId.setEnabled(false);
        tvDate.setText(c.getDate());
        edtName.setText(c.getName());
        if(c.isActivate()){
            cbActivate.setChecked(true);
            btnDelete.setEnabled(false);
        }
        else{
            cbActivate.setChecked(false);
        }
//        cbActivate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(cbActivate.isChecked() == false){
//                    btnDelete.setEnabled(false);
//                }
//            }
//        });

        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int d = c.get(Calendar.DAY_OF_MONTH);
                int m = c.get(Calendar.MONTH);
                int y = c.get(Calendar.YEAR);
                DatePickerDialog dpd = new DatePickerDialog(RemoveUpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvDate.setText(dayOfMonth+"/"+(month+1)+"/"+(year));
                    }
                },y,m,d);
                dpd.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RemoveUpdateActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course c = new Course();
                c.setId(Integer.parseInt(tvId.getText().toString()));
                c.setName(edtName.getText().toString());
                if(cbActivate.isChecked()){
                    c.setActivate(true);
                }
                c.setMajor(spMajor.getSelectedItem().toString());
                c.setDate(tvDate.getText().toString());
                Toast toast = Toast.makeText(RemoveUpdateActivity.this, Boolean.toString(c.isActivate()) , Toast.LENGTH_SHORT);
                toast.show();
                sqLiteHelper.updateCourse(c);
                Intent i = new Intent(RemoveUpdateActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(tvId.getText().toString());
//                Toast toast = Toast.makeText(ActivityRemoveUpdate.this, q.getName()+" "+q.getSoTien()+" "+q.getDate()+" "+q.getCity() , Toast.LENGTH_SHORT);
//                toast.show();
                sqLiteHelper.deleteQuyenGop(id);
                Intent i = new Intent(RemoveUpdateActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void init() {
        tvId = findViewById(R.id.tvIdRemoveUpdate);
        tvDate = findViewById(R.id.tvDateRemoveUpdate);
        edtName = findViewById(R.id.edtNameRemoveUpdate);
        spMajor = findViewById(R.id.spMajorRemoveUpdate);
        cbActivate = findViewById(R.id.cbRemoveUpdateActivate);
        btnGetDate = findViewById(R.id.btnGetDateRemoveUpdate);
        btnUpdate = findViewById(R.id.btnUpdateRemoveUpdate);
        btnDelete = findViewById(R.id.btnRemoveRemoveUpdate);
        btnBack = findViewById(R.id.btnBackRemoveUpdate);
    }
}