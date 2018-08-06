package net.accedegh.studentregistration.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import net.accedegh.studentregistration.R;
import net.accedegh.studentregistration.models.Student;

import java.util.ArrayList;

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder> {

    public ArrayList<Student> studentList;
    Context _context;



    public StudentRecyclerAdapter(ArrayList<Student> studentList, Context context) {
        this.studentList = studentList;
                _context = context;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView phone;
        public TextView email;

        public StudentViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            phone = itemView.findViewById(R.id.Phone);
            email = itemView.findViewById(R.id.Email);
        }
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.from(parent.getContext()).inflate(R.layout.contact_recycler_row,parent,false);
        StudentViewHolder svh = new StudentViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student st = studentList.get(position);
        holder.name.setText(st.getName());
        holder.phone.setText(st.getPhone());
        holder.email.setText(st.getPhone());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

}
