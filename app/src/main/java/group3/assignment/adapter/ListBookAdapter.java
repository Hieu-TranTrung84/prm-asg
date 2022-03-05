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
import group3.assignment.model.Book;

public class ListBookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private List<Book> list;
    private TextView tv_id_book_sp, tv_name_book_sp;

    public ListBookAdapter(@NonNull Context context, List<Book> list) {
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
            v = inflater.inflate(R.layout.list_book, null);
        }
        Book book = list.get(position);
        tv_id_book_sp = v.findViewById(R.id.tv_id_book_sp);
        tv_id_book_sp.setText(book.getIdBook() + ". ");
        tv_name_book_sp = v.findViewById(R.id.tv_name_book_sp);
        tv_name_book_sp.setText(book.getName());
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_book, null);
        }
        Book book = list.get(position);
        tv_id_book_sp = v.findViewById(R.id.tv_id_book_sp);
        tv_id_book_sp.setText(book.getIdBook() + ". ");

        tv_name_book_sp = v.findViewById(R.id.tv_name_book_sp);
        tv_name_book_sp.setText(book.getName());
        return v;
    }
}
