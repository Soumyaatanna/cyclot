package hcl.esg.ebike.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import de.hdodenhof.circleimageview.CircleImageView;

public class DamagedAdapter extends FirebaseRecyclerAdapter<Cycles,DamagedAdapter.myViewHolder> {

    DatabaseReference database= FirebaseDatabase.getInstance().getReference().child("cycles");
    public DamagedAdapter(@NonNull FirebaseRecyclerOptions<Cycles> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Cycles model) {
        holder.id.setText(model.getCid());
        holder.Repaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.child("10276").child("damaged").setValue("no");
                database.child("10276").child("History").setValue("Repaied");
                Toast.makeText(v.getContext(), "Repaired Updated Database", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.damage_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        TextView id;
        Button Repaired;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            id=(TextView)itemView.findViewById(R.id.cid);
            Repaired=(Button) itemView.findViewById(R.id.repair);


        }
    }
}


