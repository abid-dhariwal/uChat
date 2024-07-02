package com.example.uchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserName extends AppCompatActivity {
TextInputEditText etUserName;
Button btnLetMeIn;
ProgressBar PBUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        btnLetMeIn.setOnClickListener(v -> {
            String username = etUserName.getText().toString().trim();
            if (!username.isEmpty()) {
                saveUsernameToFirebase(username);
            } else {
                Toast.makeText(UserName.this, "Please enter a username", Toast.LENGTH_SHORT).show();
            }
            PBUserName.setVisibility(View.VISIBLE);
        });
    }
    private void saveUsernameToFirebase(String username) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usernames");

        myRef.push().setValue(username).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(UserName.this, "Username saved", Toast.LENGTH_SHORT).show();
                navigateToHomeActivity();
            } else {
                Toast.makeText(UserName.this, "Failed to save username", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void navigateToHomeActivity() {
        Intent intent = new Intent(UserName.this, Home.class);
        startActivity(intent);
    }
    private void init(){
    etUserName=findViewById(R.id.etUsername);
    btnLetMeIn=findViewById(R.id.btnLet_Me_In);
    PBUserName=findViewById(R.id.ProgressBarUN);
    PBUserName.setVisibility(View.GONE);

    }
}