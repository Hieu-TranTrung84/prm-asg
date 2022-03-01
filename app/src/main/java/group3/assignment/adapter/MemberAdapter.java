package group3.assignment.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import group3.assignment.R;
import group3.assignment.fragment.MemberFragment;
import group3.assignment.listener.MemberClickListener;
import group3.assignment.model.Member;

public class MemberAdapter extends RecyclerView.Adapter<MemberViewHolder> {
    private Context context;
    private MemberFragment fragment;
    private List<Member> list;
    private MemberClickListener listener;

    public MemberAdapter(Context context, MemberFragment fragment, List<Member> list) {
        this.context = context;
        this.fragment = fragment;
        this.list = list;

    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MemberViewHolder(LayoutInflater.from(context).inflate(R.layout.items_member, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        holder.tv_name_member.setText("Name: " + list.get(position).getName());
        holder.tv_id_member.setText("Id: " + list.get(position).getIdMember());
        holder.tv_id_member.setVisibility(View.GONE);
        holder.tv_dob_member.setText("Year of Birth: " + list.get(position).getDob());
        holder.img_delete_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////                fragment.delete(String.valueOf(list.get(position).getIdMember()));
//                Member member = list.get(position);
                listener.onItemLongPressed(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemLongClick(int pos);
    }
}

class MemberViewHolder extends RecyclerView.ViewHolder {
    protected TextView tv_name_member, tv_dob_member, tv_id_member;
    protected ImageView img_delete_member;

    public MemberViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_id_member = itemView.findViewById(R.id.tv_id_member);
        tv_name_member = itemView.findViewById(R.id.tv_name_member);
        tv_dob_member = itemView.findViewById(R.id.tv_dob_member);
        img_delete_member = itemView.findViewById(R.id.img_delete_member);
    }
}