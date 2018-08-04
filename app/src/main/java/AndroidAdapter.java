import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import net.accedegh.studentregistration.R;
import net.accedegh.studentregistration.models.AndroidVersion;

import java.util.ArrayList;

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.AndroidAdapterViewHolder> {

   public ArrayList<AndroidVersion> androidVersions;


    public AndroidAdapter(ArrayList<AndroidVersion> androidVersions) {
        this.androidVersions = androidVersions;
    }

    //Define Views for the recycler
    public class AndroidAdapterViewHolder extends RecyclerView.ViewHolder {
        public EditText name;
        public EditText version;
        public EditText year;
        public AndroidAdapterViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.android_name);
            version = itemView.findViewById(R.id.android_version);
            year = itemView.findViewById(R.id.android_version);

        }
    }


    //Inflate and return the ViewHolder
    @NonNull
    @Override
    public AndroidAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.android_row,parent,false);
        AndroidAdapterViewHolder avh = new AndroidAdapterViewHolder(v);
        return avh;
    }

    // fill holder with data from the adapter at its current item
    @Override
    public void onBindViewHolder(@NonNull AndroidAdapterViewHolder holder, int position) {
        AndroidVersion currentItem = androidVersions.get(position);

         }

    // gets the total size of the array to populate
    @Override
    public int getItemCount() {
        return androidVersions.size();
    }


}
