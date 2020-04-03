package com.example.sampleapidesign.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapidesign.Models.UserProfile;
import com.example.sampleapidesign.R;

import java.util.List;

public class UsersProfileAdapter extends RecyclerView.Adapter<UsersProfileAdapter.UserProfileViewHolder> {


    private Context context;
    private List<UserProfile> userProfileList;

    public UsersProfileAdapter(Context context, List<UserProfile> userProfileList) {
        this.context = context;
        this.userProfileList = userProfileList;
    }

    @NonNull
    @Override
    public UserProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_users, parent, false);
        return new UserProfileViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserProfileViewHolder holder, int position) {
        UserProfile userProfile = userProfileList.get(position);
        holder.textViewName.setText(userProfile.getName());
        holder.textViewPassword.setText(userProfile.getPassword());
        holder.textViewImageUrl.setText(userProfile.getImageurl());
    }

    @Override
    public int getItemCount() {
        return userProfileList.size();
    }

    class UserProfileViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName, textViewPassword, textViewImageUrl;

       public UserProfileViewHolder(@NonNull View itemView){
           super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPassword = itemView.findViewById(R.id.textViewPassword);
            textViewImageUrl = itemView.findViewById(R.id.textViewImageUrl);
       }
    }
}
