package net.accedegh.studentregistration.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import net.accedegh.studentregistration.R;
import net.accedegh.studentregistration.models.StudentDbHelper;
import net.accedegh.studentregistration.adapters.ContactCursorAdapter;
import net.accedegh.studentregistration.fragments.NoContact;

import org.w3c.dom.Text;

public class ConatctsList extends AppCompatActivity implements SearchView.OnQueryTextListener  {


    private ListView listView;
    private FloatingActionButton fab;
    private Button btnSave, btnCancel;
    private SwipeRefreshLayout swipe;
    private AlertDialog alertDialog;
    private ContactCursorAdapter studentAdapter;
    private StudentDbHelper db;
    private SearchView editsearch;
    private FrameLayout noData;
    private View view;
    private ImageView dial;
    private TextView callerId;

    NoContact ns;
    private Menu menu;
    private MenuItem itemDelete, itemEdit;
    long selectId;
    private String tag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        listView = findViewById(R.id.mylist);
        fab = findViewById(R.id.fab);
        swipe= findViewById(R.id.swipe);
        ns = new NoContact();


        //Set toolbar to your custom toolbar
        Toolbar  myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

       editsearch =  findViewById(R.id.search);
       editsearch.setOnQueryTextListener(this);
       noData = findViewById(R.id.noDataFrame);
       //dial = findViewById(R.id.dial);

       db = new StudentDbHelper(ConatctsList.this);

        final Cursor cursor = db.getAllStudents();
        studentAdapter = new ContactCursorAdapter(ConatctsList.this,cursor);
        listView.setAdapter(studentAdapter);

        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        //check
        CheckData();



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position,  long id) {
                //show action menu on long click
                itemDelete.setVisible(true);
                itemEdit.setVisible(true);
                selectId = id;
                //Return true to prevent continuous processing!
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ConatctsList.this, "clicked", Toast.LENGTH_SHORT).show();

                String ID = String.valueOf(id);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                TextView phone = view.findViewById(R.id.Phone);
                intent.setData(Uri.parse("tel:"+phone.getText().toString()));
                startActivity(intent);


            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(ConatctsList.this);
                view = View.inflate(ConatctsList.this,R.layout.student_dialog, null);
                alert.setView(view);
                alertDialog = alert.create();
                alertDialog.show();
                btnSave = view.findViewById(R.id.save);
                btnCancel = view.findViewById(R.id.cancel);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText name = view.findViewById(R.id.Name);
                        EditText phone = view.findViewById(R.id.phone);
                        EditText email = view.findViewById(R.id.Email);

                        String _name = name.getText().toString();
                        String  _phone = phone.getText().toString();
                        String _email =email.getText().toString();

                        if(_name.isEmpty()){
                            name.setError("Name cannot be empty!");
                            return;
                        }

                        if(_phone.isEmpty()){
                            phone.setError("Phone cannot be empty!");
                            return;
                        }

                        tag = _name.substring(0,1);
                        boolean result =
                                db.addStudent(_name,_phone,_email, tag);
                        if(result == true){
                            alertDialog.hide();
                            studentAdapter.contactName =_name;
                            Cursor newCursor = db.getAllStudents();
                            studentAdapter.changeCursor(newCursor);
                            CheckData();
                            Toast.makeText(ConatctsList.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            alertDialog.hide();
                            Toast.makeText(ConatctsList.this, "Canceled", Toast.LENGTH_SHORT).show();
                            CheckData();
                        }
                      }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //close dialog on cancel
                        alertDialog.hide();
                    }
                });
            }
        });
    }



    @Override
    public boolean onQueryTextSubmit(final String query) {
        Cursor cs =  db.searchStudent(query);
        studentAdapter.changeCursor(cs);
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Cursor cs =  db.searchStudent(newText);
                studentAdapter.changeCursor(cs);
                CheckData();
            }
        });
        return false;
    }

    public void CheckData() {
        if (ns.getView() == null && listView.getAdapter().getCount() == 0) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.noDataFrame, ns)
                    //.addToBackStack()
                    .commit();
        } else if (ns.getView() != null &&listView.getAdapter().getCount() >0) {
            getSupportFragmentManager()
                    .beginTransaction().
                    remove(ns).commit();

        }
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar, menu);
        itemDelete = menu.findItem(R.id.delete_menu);
        itemEdit = menu.findItem(R.id.edit_menu);

        itemDelete.setVisible(false);
        itemEdit.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.delete_menu:
               // View editView = getLayoutInflater().inflate();
                AlertDialog.Builder dialog = new AlertDialog.Builder(ConatctsList.this);
                dialog.setMessage("Are you sure you want to delete?");
                dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String string =String.valueOf(selectId);
                        db.deleteStudent(string);
                        Cursor ne = db.getAllStudents();
                        studentAdapter.changeCursor(ne);
                        CheckData();
                        Toast.makeText(ConatctsList.this, "Deleting", Toast.LENGTH_SHORT).show();
                        itemDelete.setVisible(false);
                        itemEdit.setVisible(false);
                    }
                });

                dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(ConatctsList.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                return true;
            case R.id.edit_menu:
                final AlertDialog.Builder editDialog = new AlertDialog.Builder(ConatctsList.this);
                View editView = getLayoutInflater().inflate(R.layout.update_student_dialog,null);
                editDialog.setView(editView);
                final AlertDialog ad = editDialog.create();
                Button update = editView.findViewById(R.id.update);
                Button cancel = editView.findViewById(R.id.cancel);
                final EditText name = editView.findViewById(R.id.Name);
                final EditText phone = editView.findViewById(R.id.phone);
                final EditText email = editView.findViewById(R.id.Email);


                final String userId = String.valueOf(selectId);
                Cursor cs = db.getAllStudetById(userId);
                if(cs.getCount()>=1){
                    cs.moveToFirst();
                    String _name =  cs.getString(cs.getColumnIndexOrThrow("Name"));
                    String _phone = cs.getString(cs.getColumnIndexOrThrow("Phone"));
                    String _email = cs.getString(cs.getColumnIndexOrThrow("Email"));

                    name.setText(_name);
                    phone.setText(_phone);
                    email.setText(_email);
                    tag = _name.substring(0,1);

                }

                cs.close();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        db.UpdateStudent( userId ,name.getText().toString(),
                        phone.getText().toString(),email.getText().toString(),tag);
                        Cursor eduitCursor = db.getAllStudents();
                        studentAdapter.changeCursor(eduitCursor);
                        CheckData();
                        Toast.makeText(ConatctsList.this, "Student Updated Successfully", Toast.LENGTH_SHORT).show();
                        itemDelete.setVisible(false);
                        itemEdit.setVisible(false);
                        ad.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     ad.dismiss();
                        Toast.makeText(ConatctsList.this, "Update Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
              ad.show();
                return true;
               }

        return super.onOptionsItemSelected(item);
    }

}
