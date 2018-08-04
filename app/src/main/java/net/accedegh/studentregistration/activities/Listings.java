package net.accedegh.studentregistration.activities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import net.accedegh.studentregistration.R;

import java.util.ArrayList;

public class Listings extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListView myList;
    private ArrayList<String> colours = new ArrayList<String>();
    private ArrayAdapter adapter;
    private SwipeRefreshLayout swipe;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        colours.add("Red");
        colours.add("Blue");
        colours.add("Green");
        colours.add("Yellow");

        myList = findViewById(R.id.list);
        swipe = findViewById(R.id.swipe);
        adapter = new ArrayAdapter(Listings.this,android.R.layout.simple_list_item_1,colours);
        myList.setAdapter(adapter);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Toast.makeText(Listings.this, "loading data from server..", Toast.LENGTH_SHORT).show();
                swipe.setRefreshing(false);
            }
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemClicked = myList.getAdapter().getItem(position).toString();
                Toast.makeText(Listings.this,itemClicked, Toast.LENGTH_SHORT).show();
            }
        });


        sp = getPreferences(MODE_PRIVATE);



        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(Listings.this);
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete this item?");
                alert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(adapter.getItem(position));
                        adapter.notifyDataSetChanged();
                        Toast.makeText(Listings.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Listings.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
                return false;
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }
}
