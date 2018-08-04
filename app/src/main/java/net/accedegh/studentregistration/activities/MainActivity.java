package net.accedegh.studentregistration.activities;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import net.accedegh.studentregistration.R;
import net.accedegh.studentregistration.adapters.ContactAdapter;
import net.accedegh.studentregistration.models.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ListView lv;
    private ArrayList<Student> students = new ArrayList<Student>();
    private ContactAdapter studentAdapter;
    private SearchView editsearch;
    private SharedPreferences sp;
    private Button btnSave, btnCancel;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // final StudentDbHelper sh = new StudentDbHelper(MainActivity.this);

        students.add(new Student(123,"Frank Odoom","0266275605","f.odoom@outlook.com"));
        students.add(new Student(124,"Eliezer Gyan","0248693803","eliezergyan@gmail.com"));

        lv = findViewById(R.id.mylist);
        fab = findViewById(R.id.fab);



         studentAdapter = new ContactAdapter
                (MainActivity.this,R.layout.studentlist_row,students);
        lv.setAdapter(studentAdapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
             View v = getLayoutInflater().inflate(R.layout.student_dialog,null);

            final EditText name = v.findViewById(R.id.Name);
            final EditText phone = v.findViewById(R.id.phone);
            final EditText email = v.findViewById(R.id.Email);

             btnSave = v.findViewById(R.id.save);
             btnCancel = v.findViewById(R.id.cancel);
             alert.setView(v);

             final AlertDialog myDialog = alert.create();
             myDialog.show();


             btnSave.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     String uName = name.getText().toString();
                     String uPhone = phone.getText().toString();
                     String uEmail = email.getText().toString();

                    // boolean result = sh.addStudent(uName,uPhone,uEmail);
                    /* if(result==true){
                         myDialog.dismiss();
                         Toast.makeText(MainActivity.this, "saved successfully", Toast.LENGTH_SHORT).show();
                     }
                     else{
                         Toast.makeText(MainActivity.this, "something went wrong!", Toast.LENGTH_SHORT).show();
                     }*/
                 }
             });

             btnCancel.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(MainActivity.this, "cancelling...", Toast.LENGTH_SHORT).show();
                     myDialog.hide();
                 }
             });

            }
        });
    }




    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        studentAdapter.filter(text);
        return false;

}
}
