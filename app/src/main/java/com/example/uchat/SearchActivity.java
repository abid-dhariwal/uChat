package com.example.uchat;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class SearchActivity extends AppCompatActivity {
ImageButton btn_Search,btnBack;
EditText SearchBar;
RecyclerView recyclerView;
SearchUserRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        SearchBar.requestFocus();
        btn_Search.setOnClickListener(v -> {
        String input=SearchBar.getText().toString();
        if (input.isEmpty()){
        SearchBar.setError("Invalid username!");
        return;
        }
            setRecyclerView();
        });
        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

    }
    private void init(){
        SearchBar=findViewById(R.id.Searchbar);
        btn_Search=findViewById(R.id.btn_Search);
        btnBack=findViewById(R.id.btnBack);
        recyclerView=findViewById(R.id.SearchUser_recyclerview);
    }
    private void setRecyclerView(){
     Query query=FirebaseUtil.allUserCollectionReference()
             .whereGreaterThanOrEqualTo("username",SearchBar);
     FirestoreRecyclerOptions<UserModel> options=new FirestoreRecyclerOptions.Builder<UserModel>()
             .setQuery(query,UserModel.class).build();
    adapter=new SearchUserRecyclerAdapter(options,getApplicationContext());
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
    adapter.startListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter!=null){
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter!=null){
            adapter.stopListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter!=null){
            adapter.startListening();
        }
    }
}