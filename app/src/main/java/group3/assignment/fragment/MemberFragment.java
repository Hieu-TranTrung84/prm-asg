package group3.assignment.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import group3.assignment.R;
import group3.assignment.adapter.MemberAdapter;
import group3.assignment.dao.MemberDAO;
import group3.assignment.listener.MemberClickListener;
import group3.assignment.model.Member;


public class MemberFragment extends Fragment implements MemberClickListener {
    private RecyclerView recyclerView;
    private List<Member> list;
    private MemberDAO dao;
    private MemberAdapter adapter;
    private Member item;
    private FloatingActionButton fab;
    private Dialog dialog;
    private EditText edt_name_member, edt_dob_member, edt_id_member;
    private Button btn_save_member, btn_cancel_member;
    private MemberClickListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_member, container, false);
        recyclerView = v.findViewById(R.id.rv_member);
        dao = new MemberDAO(getActivity());
        fab = v.findViewById(R.id.fab);
        list = new ArrayList<>();

        showMember();
        //insert
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        //update

        return v;
    }

    //show member lên recyclerView
    public void showMember() {
        list = dao.getAll();
        adapter = new MemberAdapter(getActivity(), this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    //làm func delete cho icon delete
    public void delete(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Do you want to delete ?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                showMember();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        builder.show();
    }

    //cái dialog này để làm func insert và update
    public void openDialog(Context context, final int check) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_member);
        edt_name_member = dialog.findViewById(R.id.edt_name_member);
        edt_id_member = dialog.findViewById(R.id.edt_id_member);
        edt_id_member.setEnabled(false);
        edt_id_member.setVisibility(View.GONE);

        edt_dob_member = dialog.findViewById(R.id.edt_dob_member);
        btn_save_member = dialog.findViewById(R.id.btn_save_member);
        btn_cancel_member = dialog.findViewById(R.id.btn_cancel_member);
        //kiem tra bien check insert 0 hay update 1
        if (check != 0) {
            edt_id_member.setText(String.valueOf(item.getIdMember()));
            edt_name_member.setText(item.getName());
            edt_dob_member.setText(item.getDob());
        }

        btn_cancel_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Member();
                item.setName(edt_name_member.getText().toString());
                item.setDob(edt_dob_member.getText().toString());
                if (checkValidate() > 0) {
                    //insert 0
                    if (check == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Insert Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //update
                        item.setIdMember(Integer.parseInt(edt_id_member.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Update Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    showMember();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int checkValidate() {
        int check = 1;
        if (edt_name_member.getText().length() == 0 || edt_dob_member.getText().length() == 0) {
            Toast.makeText(getContext(), "Fill In All Information", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    @Override
    public void onItemLongPressed(int index) {

    }
}