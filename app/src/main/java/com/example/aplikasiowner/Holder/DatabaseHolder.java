package com.example.aplikasiowner.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiowner.Adapter.RequestDatabase;
import com.example.aplikasiowner.R;

public class DatabaseHolder extends RecyclerView.ViewHolder {

    public TextView tusername, talamat;
    public CardView bcardView;

    public DatabaseHolder(@NonNull View itemView) {
        super(itemView);
        tusername = itemView.findViewById(R.id.namapetani);
        talamat = itemView.findViewById(R.id.lokasipetani);
        bcardView = itemView.findViewById(R.id.card);
    }

    public void bindToDatabase(RequestDatabase requestDatabase, View.OnClickListener onClickListener){
        tusername.setText(requestDatabase.username);
        talamat.setText(requestDatabase.alamat);
        bcardView.setOnClickListener(onClickListener);
    }



}
