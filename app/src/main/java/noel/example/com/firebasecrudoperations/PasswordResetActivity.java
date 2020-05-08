package noel.example.com.firebasecrudoperations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {
    EditText email;
    Button reset,back;
    ProgressBar progress;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        email=findViewById(R.id.edit_resetemail);

        reset=findViewById(R.id.button_reset);

        back=findViewById(R.id.button_back);

        progress=findViewById(R.id.resetprogressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordResetActivity.this.finish();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString().trim();

                if (TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(PasswordResetActivity.this, "Enter your register email id", Toast.LENGTH_SHORT).show();
                    return;
                }

                progress.setVisibility(View.VISIBLE);


                firebaseAuth.sendPasswordResetEmail(userEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PasswordResetActivity.this, "Instructions sended to reset your password!", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(PasswordResetActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();

                                }

                                progress.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }
}
