package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentsViewHolder> {
    private ArrayList<Student> students;
    private Context context;

    public StudentAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public StudentsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
      View view = LayoutInflater.from(context).inflate(R.layout.student_item,viewGroup,false);
        return new StudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentsViewHolder holder, int position) {
        Student obj = students.get(position);
        holder.name.setText(obj.getNam());

        holder.itemView.setOnClickListener( v ->
        {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name", obj.getNam());
            intent.putExtra("email", obj.getEmail());
            intent.putExtra("group", obj.getGroup());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
    public static class StudentsViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public  StudentsViewHolder (View item)
        {
            super(item);
            name = item.findViewById(R.id.nametxt);
        }
    }
}
