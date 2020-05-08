package noel.example.com.firebasecrudoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {
    EditText name,age,dob;
    Button save,display;
    DatePickerDialog datePicker;

    DatabaseReference databasepersonal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        name=findViewById(R.id.edit_name);

        age=findViewById(R.id.edit_age);

        dob=findViewById(R.id.edit_dob);



        save=findViewById(R.id.button_save);

        display=findViewById(R.id.button_display);














        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProfileActivity.this,DisplayActivity.class);
                startActivity(i);
            }
        });

        databasepersonal= FirebaseDatabase.getInstance().getReference("items");


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cald= Calendar.getInstance();
                int day=cald.get(Calendar.DAY_OF_MONTH);
                int month=cald.get(Calendar.MONTH);
                int year=cald.get(Calendar.YEAR);

                datePicker= new DatePickerDialog(ProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                dob.setText(day + "/" +(month+1) + "/" + year);
                            }
                        },year,month,day);
                datePicker.show();


            }
        });





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personName = name.getText().toString().trim();
                String personAge = age.getText().toString().trim();
                String personDOB = dob.getText().toString().trim();


                if (TextUtils.isEmpty(personName)){
                    showToast("Enter person name!");
                return;}

                if (TextUtils.isEmpty(personAge)){
                    showToast("Enter person age!");
                    return;}

                if (TextUtils.isEmpty(personDOB)){
                    showToast("Enter person date of birth!");
                    return;}




                {
                    String uid = databasepersonal.push().getKey();
                    Details detail = new Details(uid,personName,personAge,personDOB);

                    databasepersonal.child(uid).setValue(detail);

                    Toast.makeText(ProfileActivity.this, "Details  added", Toast.LENGTH_LONG).show();

                    name.setText("");
                    age.setText("");
                    dob.setText("");
                }
            }
        });

    }
    public void showToast(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("REGISTER")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
