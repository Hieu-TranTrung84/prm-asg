package group3.assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import group3.assignment.R;
import group3.assignment.model.BookCategory;

public class ListBookCategoryAdapter extends ArrayAdapter<BookCategory> {
    private Context context;
    private List<BookCategory> list;
    private TextView tv_id_book_category_sp, tv_name_book_category_sp;


    public ListBookCategoryAdapter(@NonNull Context context, List<BookCategory> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        //kt có null hay ko, nếu null thì tạo 1 cái layout cho nó
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_book_category, null);
        }
        BookCategory item = list.get(position);
        if (item != null) {
            tv_id_book_category_sp = v.findViewById(R.id.tv_id_book_category_sp);
            tv_id_book_category_sp.setText(item.getIdBookCategory() + ". ");

            tv_name_book_category_sp = v.findViewById(R.id.tv_name_book_category_sp);
            tv_name_book_category_sp.setText(item.getNameBookCategory());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_book_category, null);
        }
        BookCategory item = list.get(position);

        if (item != null) {
            tv_id_book_category_sp = v.findViewById(R.id.tv_id_book_category_sp);
            tv_id_book_category_sp.setText(item.getIdBookCategory() + ". ");

            tv_name_book_category_sp = v.findViewById(R.id.tv_name_book_category_sp);
            tv_name_book_category_sp.setText(item.getNameBookCategory());
        }
        return v;
    }
}
