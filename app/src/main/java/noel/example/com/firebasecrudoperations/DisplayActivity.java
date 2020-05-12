package noel.example.com.firebasecrudoperations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    DatabaseReference databasepersonal;

    ProgressDialog progressDialog;

    List<Details> list = new ArrayList<>();

    RecyclerView recyclerView;

    RecyclerView.Adapter adapter ;

    SearchView searchView;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);







        searchView=findViewById(R.id.searchView);




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayActivity.this));

        progressDialog = new ProgressDialog(DisplayActivity.this);

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();

        databasepersonal = FirebaseDatabase.getInstance().getReference("items");

        databasepersonal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                list.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Details details = dataSnapshot.getValue(Details.class);

                    list.add(details);
                }

                adapter = new DisplayAdapter(DisplayActivity.this, list);

                recyclerView.setAdapter(adapter);


                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });

        if (searchView!=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String str) {
        ArrayList<Details>mylist=new ArrayList<Details>();
        for(Details object:list){
            if (object.getPersonName().toLowerCase().contains(str.toLowerCase())){
                mylist.add(object);
            }
        }
        DisplayAdapter adapterClass=new DisplayAdapter(DisplayActivity.this, mylist);
        recyclerView.setAdapter(adapterClass);
    }



    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                Intent d=new Intent(DisplayActivity.this,LoginActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(d);
                return  true;
                default:
                    return super.onOptionsItemSelected(item);
        }
        }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }
}


