package net.accedegh.studentregistration.activities;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import net.accedegh.studentregistration.R;
import net.accedegh.studentregistration.adapters.StudentRecyclerAdapter;
import net.accedegh.studentregistration.models.Student;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ContactRecyclerView extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Student> students;
    RecyclerView.LayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_recycler_view);

        lm = new LinearLayoutManager(this);
        rv = findViewById(R.id.contact_recycler);
        students = new ArrayList<>();
        students.add(new Student(1,"Frank Odoom","0266275605","f.odoom@outlook.com"));
        students.add(new Student(1,"James Tagoe","02776275353","j.tagoe@outlook.com"));
        students.add(new Student(1,"Samed BisMark","0267383833","s.bismark@outlook.com"));
        students.add(new Student(1,"Ben Afful","0266275605","af.afful@yaooo.com"));

        StudentRecyclerAdapter sp = new StudentRecyclerAdapter(students,ContactRecyclerView.this);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setAdapter(sp);



    }
}
