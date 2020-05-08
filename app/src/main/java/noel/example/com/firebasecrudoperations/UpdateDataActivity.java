package noel.example.com.firebasecrudoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDataActivity extends AppCompatActivity {

    EditText name,age,dob;
    Button updates,backs;

    DatabaseReference databasepersonal;

    String names,ages,dobs,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        Intent i=getIntent();
        name=findViewById(R.id.edit_name);

        age=findViewById(R.id.edit_age);

        dob=findViewById(R.id.edit_dob);





        updates=findViewById(R.id.updates);

        backs=findViewById(R.id.button_backs);


        id=i.getStringExtra("id");
        names=i.getStringExtra("name");
        ages=i.getStringExtra("age");
        dobs=i.getStringExtra("dob");



        name.setText(names);


        age.setText(ages);


        dob.setText(dobs);





        updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databasepersonal = FirebaseDatabase.getInstance().getReference("items").child(id);
                String uname,uage,udob;

                uname=name.getText().toString();
                uage=age.getText().toString();
                udob=dob.getText().toString();


                Details detail = new Details(id,uname,uage,udob);
                databasepersonal.setValue(detail);
                Toast.makeText(UpdateDataActivity.this, "Updated data", Toast.LENGTH_SHORT).show();



            }
        });

        backs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(UpdateDataActivity.this,DisplayActivity.class);
               startActivity(intent);

            }
        });
    }
}
