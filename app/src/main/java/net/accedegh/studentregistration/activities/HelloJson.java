package net.accedegh.studentregistration.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wang.avi.AVLoadingIndicatorView;

import net.accedegh.studentregistration.R;

import java.net.HttpURLConnection;

public class HelloJson extends AppCompatActivity {

    private Button btnFetch;
    private TextView viewJson;

    private ProgressDialog pd;
    private AVLoadingIndicatorView av;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_json);

        btnFetch = findViewById(R.id.btn_fetchJson);
        viewJson = findViewById(R.id.json);
        pd = new ProgressDialog(this);
        av = findViewById(R.id.loader);
        av = new AVLoadingIndicatorView(this);

         av.smoothToHide();


        pd.setMessage("fetching data!...");
        pd.setCancelable(false);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               av.show();
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(HelloJson.this);
                String url = "https://jsonplaceholder.typicode.com/posts";
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                viewJson.setText(response);
                               av.smoothToHide();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        viewJson.setText(error.getMessage());
                        av.smoothToHide();
                    }
                });

               // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });
    }
}
