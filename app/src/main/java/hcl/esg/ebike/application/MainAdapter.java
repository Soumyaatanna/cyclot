package hcl.esg.ebike.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<User,MainAdapter.myViewHolder> {


    public MainAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull User model) {
        holder.name.setText(model.getName());
        holder.id.setText(model.getEmp_id());
        holder.email.setText(model.getEmail());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        TextView name,id,email;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            name=(TextView)itemView.findViewById(R.id.nametext);
            id=(TextView)itemView.findViewById(R.id.empid);
            email=(TextView)itemView.findViewById(R.id.email);
        }
    }
}
