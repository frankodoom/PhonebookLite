package net.accedegh.studentregistration.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.accedegh.studentregistration.R;
import net.accedegh.studentregistration.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactAdapter extends ArrayAdapter<Student> {

    private Context _context;
    private ArrayList<Student> _studentList;
    private List<Student> _queryList = null;


    public ContactAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Student> studentList) {
        super(context, resource, studentList);
        _context = context;
        _studentList =studentList;
        _queryList = studentList;
    }

    @Override
    public int getCount(){
        int count = _studentList.size();
        return count;
    }


    public View getView(int position, View view, ViewGroup parent){
        View _view = view;
       LayoutInflater inflater = (LayoutInflater) getContext()
               .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _view = inflater.inflate(R.layout.studentlist_row,null);
        TextView name =   _view.findViewById(R.id.Name);
        TextView email = _view.findViewById(R.id.Email);
        TextView phone = _view.findViewById(R.id.Phone);

        name.setText(_studentList.get(position).Name);
        email.setText(_studentList.get(position).Email);
        phone.setText(_studentList.get(position).Phone);
        return _view;
    };


    // Filter Class
    public void filter(String charText) {
        //charText = charText.toLowerCase(Locale.getDefault());
        _queryList.clear();
        if (charText.length() == 0) {
            _queryList.addAll(_studentList);
        } else {
            for (Student wp : _studentList) {
                if (wp.getName().contains(charText)) {
                    _studentList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
