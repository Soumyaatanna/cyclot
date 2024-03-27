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

public class SecurityAdapter extends FirebaseRecyclerAdapter<Cycles,SecurityAdapter.myViewHolder> {

    DatabaseReference database= FirebaseDatabase.getInstance().getReference().child("cycles");
    public SecurityAdapter(@NonNull FirebaseRecyclerOptions<Cycles> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Cycles model) {
        holder.id.setText(model.getCid());
         holder.RequestTime.setText(model.getRequestTime());
         holder.ReturnTime.setText(model.getReturnTime());

        String cycleId = getRef(position).getKey(); // Get the ID of the current cycle

        // Set cycle ID in the ViewHolder
        holder.id.setText(cycleId);
        holder.Damage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.child(cycleId).child("damaged").setValue("yes");
                database.child(cycleId).child("History").setValue("Damaged");
                database.child(cycleId).child("check").setValue("yes");
                Toast.makeText(v.getContext(), "Damaged Reported to admin", Toast.LENGTH_SHORT).show();
            }
        });
        holder.Approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.child(cycleId).child("check").setValue("yes");
                Toast.makeText(v.getContext(), "Approved", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.security_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        TextView id;
        TextView RequestTime,ReturnTime;
        Button Damage,Approve;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            id=(TextView)itemView.findViewById(R.id.cid);
            RequestTime=(TextView)itemView.findViewById(R.id.req_time);
            ReturnTime=(TextView)itemView.findViewById(R.id.rtn_time);
            Damage=(Button) itemView.findViewById(R.id.damage);
            Approve=(Button) itemView.findViewById(R.id.approve);

        }
    }
}


