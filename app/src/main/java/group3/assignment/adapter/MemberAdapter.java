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

import group3.assignment.model.Member;

public class MemberAdapter extends RecyclerView.Adapter<MemberViewHolder> {
    private Context context;
    private List<Member> list;
    private IClickListener listener;

    public MemberAdapter(Context context, List<Member> list, IClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    //sử dụng interface click listener để callback chúng
    public interface IClickListener {
        void onClickUpdateItem(Member member);

        void onClickDeleteItem(String id);
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MemberViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.items_member, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = list.get(position);
        holder.tv_name_member.setText("Name: " + member.getName());
        holder.tv_id_member.setText("Id: " + member.getIdMember());
        holder.tv_id_member.setVisibility(View.GONE);
        holder.tv_dob_member.setText("Year of Birth: " + member.getDob());


        holder.img_edit_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickUpdateItem(member);
            }
        });

        holder.img_delete_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDeleteItem(String.valueOf(member.getIdMember()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}

class MemberViewHolder extends RecyclerView.ViewHolder {
    protected TextView tv_name_member, tv_dob_member, tv_id_member;
    protected ImageView img_delete_member, img_edit_member;

    public MemberViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_id_member = itemView.findViewById(R.id.tv_id_member);
        tv_name_member = itemView.findViewById(R.id.tv_name_member);
        tv_dob_member = itemView.findViewById(R.id.tv_dob_member);
        img_delete_member = itemView.findViewById(R.id.img_delete_member);
        img_edit_member = itemView.findViewById(R.id.img_edit_member);
    }
}