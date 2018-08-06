package net.accedegh.studentregistration.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import net.accedegh.studentregistration.R;

public class ContactCursorAdapter extends CursorAdapter {

    public TextView contactIcon;
    private Context _context;
    public  String contactName;

    public ContactCursorAdapter(Context context, Cursor c) {
        super(context, c);
        _context = context;
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.studentlist_row, null);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name =  view.findViewById(R.id.Name);
        TextView email = view.findViewById(R.id.Email);
        TextView phone = view.findViewById(R.id.Phone);
        TextView tag  = view.findViewById(R.id.callerId);

        String Name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
        String Email = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
        String Phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
        String Tag = cursor.getString(cursor.getColumnIndexOrThrow("Tag"));

        name.setText(Name);
        email.setText(Email);
        phone.setText(Phone);
        tag.setText(Tag);
        //Set Random Color
        //tag.setBackgroundColor(_context.getResources().getColor(R.color.colorPrimary));

    }

}
