package com.example.nguyentatthangktrabai2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyentatthangktrabai2.model.Course;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CourseViewHolder>{
    private List<Course> listCourse;
    private Context context;

    public RecyclerViewAdapter(Context context) {
        listCourse = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cardviewcourse,parent,false);
        return new CourseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course c = listCourse.get(position);
        holder.tvCardId.setText(c.getId()+"");
        holder.tvCardName.setText(c.getName());
        if(c.isActivate()){
            holder.cbActivate.setChecked(true);
        }
        else{
            holder.cbActivate.setChecked(false);
        }
        holder.tvCardDate.setText(c.getDate());
        holder.tvCardMajor.setText(c.getMajor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RemoveUpdateActivity.class);
                Course c = listCourse.get(position);
                i.putExtra("course",c);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCourse.size();
    }

    public void setListOrder(List<Course> list){
        this.listCourse = list;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCardId, tvCardName, tvCardDate, tvCardMajor;
        private CheckBox cbActivate;
        public CourseViewHolder(@NonNull View v) {
            super(v);
            tvCardId = v.findViewById(R.id.tvCardID);
            tvCardName = v.findViewById(R.id.tvCardName);
            cbActivate = v.findViewById(R.id.cbCardActivate);
            tvCardDate = v.findViewById(R.id.tvCardDate);
            tvCardMajor = v.findViewById(R.id.tvCardMajor);
        }
    }
}
