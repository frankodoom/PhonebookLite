package net.accedegh.studentregistration.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import net.accedegh.studentregistration.R;
import net.accedegh.studentregistration.StudentRecyclerAdapter;
import net.accedegh.studentregistration.models.Student;

import java.util.ArrayList;

public class RecyclerViewDemo extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    ArrayList<Student> studentArrayList;
    RecyclerView recyclerView;
    StudentRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        //Populate student arrayList
        populateData();
        recyclerView = findViewById(R.id.student_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new StudentRecyclerAdapter(studentArrayList,RecyclerViewDemo.this);
        recyclerView.setAdapter(recyclerAdapter);
    }


    public void populateData(){

        studentArrayList = new ArrayList<Student>();
        studentArrayList.add(new Student(1,"Frank Odoom","0266275605","f.odooom@outlook.com"));
        studentArrayList.add(new Student(1,"Kojo Meenu","0266275605","f.odooom@outlook.com"));
        studentArrayList.add(new Student(1,"Ama  Mensah","0266275605","f.odooom@outlook.com"));
        studentArrayList.add(new Student(1,"Adjzo Adams","0266275605","f.odooom@outlook.com"));
        studentArrayList.add(new Student(1,"Kelly Rowland","0266275605","f.odooom@outlook.com"));
        studentArrayList.add(new Student(1,"Nick Cannon","0266275605","f.odooom@outlook.com"));
        studentArrayList.add(new Student(1,"James Brown","0266275605","f.odooom@outlook.com"));
        studentArrayList.add(new Student(1,"Michael Jackon","0266275605","f.odooom@outlook.com"));
        studentArrayList.add(new Student(1,"Jamesk Franko","0266275605","f.odooom@outlook.com"));
        studentArrayList.add(new Student(1,"Peter Parker","0266275605","f.odooom@outlook.com"));
        studentArrayList.add(new Student(1,"Cameron Diaz","0266275605","f.odooom@outlook.com"));

    }
}
