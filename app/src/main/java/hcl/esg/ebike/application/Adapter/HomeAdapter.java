package hcl.esg.ebike.application.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import de.hdodenhof.circleimageview.CircleImageView;
import hcl.esg.ebike.application.Models.Cycles;
import hcl.esg.ebike.application.Models.History;
import hcl.esg.ebike.application.R;

public class HomeAdapter extends FirebaseRecyclerAdapter<History,HomeAdapter.myViewHolder> {


    public HomeAdapter(@NonNull FirebaseRecyclerOptions<History> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull History model) {
        holder.cycleId.setText(model.getCycleId());
        holder.allotTime.setText(model.getAlloatTime());
        holder.empid.setText(model.empId);

        switch (model.getColor().toLowerCase()) {
            case "blue":
                holder.cycleImage.setImageResource(R.drawable.blue_cycle);
                break;
            case "black":
                holder.cycleImage.setImageResource(R.drawable.black_cycle);
                break;
            case "green":
                holder.cycleImage.setImageResource(R.drawable.green_cycle);
                break;
            case "red":
                holder.cycleImage.setImageResource(R.drawable.red_cycle);
                break;
            case "yellow":
                holder.cycleImage.setImageResource(R.drawable.yellow_cycle);
                break;
            default:
                holder.cycleImage.setImageResource(R.mipmap.ic_launcher); // default image if no match
                break;
        }
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,parent,false);
        return new myViewHolder(view);
    }
    public void updateOptions(FirebaseRecyclerOptions<History> options) {
        super.updateOptions(options); // Correctly update the options in the parent class
        startListening(); // Restart listening for changes
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        TextView cycleId,allotTime,empid;
        CircleImageView cycleImage;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            cycleId=(TextView)itemView.findViewById(R.id.cid);
            empid=(TextView)itemView.findViewById(R.id.emp_id);

             allotTime= (TextView)itemView.findViewById(R.id.allotTime);
             cycleImage = (CircleImageView) itemView.findViewById(R.id.cyc);

        }
    }
}


