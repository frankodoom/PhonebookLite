package net.accedegh.studentregistration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import net.accedegh.studentregistration.models.Student;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class StudentRecyclerAdapter extends
        RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder> {

    ArrayList<Student> studentArrayList;
    Context context;


    public StudentRecyclerAdapter(ArrayList<Student> studentArrayList, Context context) {
        this.studentArrayList = studentArrayList;
        this.context = context;
    }


    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView phone;
        TextView email;

        public StudentViewHolder(View itemView) {
            super(itemView);

            name =  itemView.findViewById(R.id.rec_name);
            phone = itemView.findViewById(R.id.rec_phone);
            email = itemView.findViewById(R.id.rec_email);
        }
    }

    @NonNull
    @Override
    public StudentRecyclerAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       LayoutInflater inflater = null ;
       View v = inflater.from(context).inflate(R.layout.recycler_row_item,parent,false);
       StudentViewHolder svh = new StudentViewHolder(v);
       return svh;

    }


    @Override
    public void onBindViewHolder(@NonNull StudentRecyclerAdapter.StudentViewHolder holder, int position) {

        holder.phone.setText(studentArrayList.get(position).getPhone());
        holder.email.setText(studentArrayList.get(position).getEmail());
        holder.name.setText(studentArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }


}