package noel.example.com.firebasecrudoperations;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

    DatabaseReference databasepersonal;

    Context context;
    List<Details> DetailList;

    public DisplayAdapter(Context context, List<Details> TempList) {

        this.DetailList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardholder, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Details personDetails = DetailList.get(position);

        holder.Name.setText(personDetails.getPersonName());

        holder.Age.setText(personDetails.getPersonAge());

        holder.Dob.setText(personDetails.getPersonDOB());



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databasepersonal=FirebaseDatabase.getInstance().getReference("items").child(personDetails.getPersonId());
                databasepersonal.removeValue();
                Toast.makeText(context, "Data deleted", Toast.LENGTH_SHORT).show();
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent( view.getContext(),UpdateDataActivity.class);
                    a.putExtra("id",personDetails.getPersonId());
                    a.putExtra("name",personDetails.getPersonName());
                    a.putExtra("age",personDetails.getPersonAge());
                    a.putExtra("dob",personDetails.getPersonDOB());


                    view.getContext().startActivity(a);
            }
        });



    }

    @Override
    public int getItemCount() {

        return DetailList.size();
    }





    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Name;
        public TextView Age;
        public TextView Dob;


        Button delete,update;



        public ViewHolder(View itemView) {

            super(itemView);

            Name = (TextView) itemView.findViewById(R.id.textViewName);

            Age = (TextView) itemView.findViewById(R.id.textViewage);

            Dob = (TextView) itemView.findViewById(R.id.textViewdob);



            delete=itemView.findViewById(R.id.delete);

            update=itemView.findViewById(R.id.update);









        }


    }



}
