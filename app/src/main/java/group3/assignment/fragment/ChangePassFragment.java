package group3.assignment.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import group3.assignment.R;
import group3.assignment.dao.EmployeeDAO;
import group3.assignment.model.Employee;


public class ChangePassFragment extends Fragment {
    private EmployeeDAO dao;
    private EditText edt_current_password, edt_new_password, edt_confirm_password;
    private Button btn_save_change, btn_cancel_change;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        mapping();
        dao = new EmployeeDAO(getActivity());
        btn_cancel_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_current_password.setText("");
                edt_new_password.setText("");
                edt_confirm_password.setText("");
            }
        });
        btn_save_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("EMP_FILE", Context.MODE_PRIVATE);
                String username = pref.getString("USERNAME", "");
                if (checkInvalid() > 0) {
                    Employee employee = dao.getUsername(username);
                    employee.setPassword(edt_new_password.getText().toString());
                    dao.update(employee);
                    Log.i("================","Current Password bution");
                    if (dao.update(employee) > 0) {
                        Toast.makeText(getContext(), "Change Password Successful", Toast.LENGTH_SHORT).show();
                        edt_current_password.setText("");
                        edt_new_password.setText("");
                        edt_confirm_password.setText("");
                    } else {
                        Toast.makeText(getContext(), "Change Password Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    private void mapping() {
        edt_current_password = view.findViewById(R.id.edt_current_password);
        edt_new_password = view.findViewById(R.id.edt_new_password);
        edt_confirm_password = view.findViewById(R.id.edt_confirm_password);
        btn_save_change = view.findViewById(R.id.btn_save_change);
        btn_cancel_change = view.findViewById(R.id.btn_cancel_change);
    }

    public int checkInvalid() {
        int check = 1;
        if (edt_current_password.getText().length() == 0 || edt_new_password.getText().length() == 0 ||
                edt_confirm_password.getText().length() == 0) {
            Toast.makeText(getContext(), "Fill In All Information", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            //kt password dẵ lưu trong EMP_FILE để so sánh
            SharedPreferences pref = getActivity().getSharedPreferences("EMP_FILE", Context.MODE_PRIVATE);

            String currentPass = pref.getString("PASSWORD", "");
            String newPass = edt_new_password.getText().toString();
            String confirmPass = edt_confirm_password.getText().toString();
            //kt current password có đúng ko
            if (!currentPass.equals(edt_current_password.getText().toString())) {
                Toast.makeText(getContext(), "Wrong Current Password", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            //kt có trùng khớp ko
            if (!newPass.equals(confirmPass)) {
                Toast.makeText(getContext(), "Not Match Password", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}