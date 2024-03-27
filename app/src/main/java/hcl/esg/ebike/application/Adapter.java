package hcl.esg.ebike.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;





import de.hdodenhof.circleimageview.CircleImageView;

    public class Adapter extends FirebaseRecyclerAdapter<Cycles,Adapter.myViewHolder> {


        public Adapter(@NonNull FirebaseRecyclerOptions<Cycles> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Cycles model) {
            holder.id.setText(model.getCid());
            holder.color.setText(model.getColor());
            holder.available.setText(model.getAvailability());
            holder.location.setText(model.getLocation());


        }

        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
            return new myViewHolder(view);
        }

        class myViewHolder extends RecyclerView.ViewHolder{


            TextView id,color,available,location;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);


                id=(TextView)itemView.findViewById(R.id.cid);
                color=(TextView)itemView.findViewById(R.id.color);
                available=(TextView)itemView.findViewById(R.id.availability);
                location=(TextView)itemView.findViewById(R.id.Location);
            }
        }
    }


