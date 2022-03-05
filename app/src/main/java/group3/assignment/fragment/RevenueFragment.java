package group3.assignment.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import group3.assignment.R;
import group3.assignment.dao.CardDAO;

public class RevenueFragment extends Fragment {
    private Button btn_from_date, btn_to_date, btn_revenue;
    private EditText edt_from_date, edt_to_date;
    private TextView tv_revenue;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private int year, month, day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_revenue, container, false);
        btn_from_date = v.findViewById(R.id.btn_from_date);
        btn_to_date = v.findViewById(R.id.btn_to_date);
        btn_revenue = v.findViewById(R.id.btn_revenue);
        edt_from_date = v.findViewById(R.id.edt_from_date);
        edt_to_date = v.findViewById(R.id.edt_to_date);
        tv_revenue = v.findViewById(R.id.tv_revenue);
        btn_from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(getActivity(), 0, fromDate,
                        year, month, day);
                dpd.show();
            }
        });
        btn_to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(getActivity(), 0, toDate,
                        year, month, day);
                dpd.show();
            }
        });
        btn_revenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formDate = edt_from_date.getText().toString();
                String toDate = edt_to_date.getText().toString();
                CardDAO dao = new CardDAO(getActivity());
                tv_revenue.setText("Revenue: " + dao.getRevenue(formDate, toDate));
            }
        });
        return v;
    }

    DatePickerDialog.OnDateSetListener fromDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int y, int m, int d) {
            year = y;
            month = m;
            day = d;
            GregorianCalendar c = new GregorianCalendar(year, month, day);
            edt_from_date.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener toDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int y, int m, int d) {
            year = y;
            month = m;
            day = d;
            GregorianCalendar c = new GregorianCalendar(year, month, day);
            edt_to_date.setText(sdf.format(c.getTime()));
        }
    };


}