package com.example.aplikasiowner.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplikasiowner.Adapter.RequestDatabase;
import com.example.aplikasiowner.Holder.DatabaseHolder;
import com.example.aplikasiowner.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Dashboard extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<RequestDatabase, DatabaseHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = findViewById(R.id.datapetani);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        Query query = getQuery(mDatabase);
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<RequestDatabase>()
                .setQuery(query, RequestDatabase.class)
                .build();
        mAdapter = new FirebaseRecyclerAdapter<RequestDatabase, DatabaseHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DatabaseHolder databaseHolder, int i, @NonNull RequestDatabase requestDatabase) {
                databaseHolder.bindToDatabase(requestDatabase, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            @NonNull
            @Override
            public DatabaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
               return new DatabaseHolder(inflater.inflate(R.layout.data_petani, parent, false));
            }
        };
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase) {
        Query query = mDatabase.child("Data User");
        return query;
    }
}