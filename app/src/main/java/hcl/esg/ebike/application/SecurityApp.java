package hcl.esg.ebike.application;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SecurityApp extends AppCompatActivity {

    RecyclerView recyclerView;
    SecurityAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_app);

        recyclerView = (RecyclerView)findViewById(R.id.rvv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Cycles> options =
                new FirebaseRecyclerOptions.Builder<Cycles>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cycles").orderByChild("check").equalTo("no"), Cycles.class)
                        .build();

        mainAdapter = new SecurityAdapter(options);
        recyclerView.setAdapter(mainAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }


}