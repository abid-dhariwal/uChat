package com.example.uchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SearchUserRecyclerAdapter extends FirestoreRecyclerAdapter<UserModel,SearchUserRecyclerAdapter.UserModelViewHolder> {
Context context;

    public SearchUserRecyclerAdapter(@NonNull FirestoreRecyclerOptions<UserModel> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserModelViewHolder holder, int position, @NonNull UserModel model) {
      holder.tvUserName.setText(model.getUsername());
      holder.tvPhoneNumber.setText(model.getPhoneNumber());
    }

    @NonNull
    @Override
    public UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_activity_recycler_row,parent,false);
        return new  UserModelViewHolder(view);
    }

    class UserModelViewHolder extends RecyclerView.ViewHolder{
    TextView tvUserName,tvPhoneNumber;
    ImageView profile_pic;
        public UserModelViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName=itemView.findViewById(R.id.tvUsername);
            tvPhoneNumber=itemView.findViewById(R.id.tvPhoneNumber);
            profile_pic=itemView.findViewById(R.id.profile_pic);
        }
    }
}
