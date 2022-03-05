package group3.assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import group3.assignment.R;
import group3.assignment.model.Member;

public class ListMemberAdapter extends ArrayAdapter<Member> {
    private Context context;
    private List<Member> list;
    private TextView tv_id_member_sp, tv_name_member_sp;

    public ListMemberAdapter(@NonNull Context context, List<Member> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_member, null);
        }
        Member item = list.get(position);
        if (item != null) {
            tv_id_member_sp = v.findViewById(R.id.tv_id_member_sp);
            tv_id_member_sp.setText(item.getIdMember() + ". ");
            tv_name_member_sp = v.findViewById(R.id.tv_name_member_sp);
            tv_name_member_sp.setText(item.getName());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_member, null);
        }
        Member item = list.get(position);
        if (item != null) {
            tv_id_member_sp = v.findViewById(R.id.tv_id_member_sp);
            tv_id_member_sp.setText(item.getIdMember() + ". ");
            tv_name_member_sp = v.findViewById(R.id.tv_name_member_sp);
            tv_name_member_sp.setText(item.getName());
        }
        return v;
    }
}
