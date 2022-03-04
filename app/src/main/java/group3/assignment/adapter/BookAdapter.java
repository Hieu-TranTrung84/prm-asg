package group3.assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import group3.assignment.R;
import group3.assignment.dao.BookCategoryDAO;
import group3.assignment.model.Book;
import group3.assignment.model.BookCategory;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {

    private Context context;
    private List<Book> list;
    private IClickListener listener;

    public BookAdapter(Context context, List<Book> list, IClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public interface IClickListener {
        void onClickUpdateItem(Book book);

        void onClickDeleteItem(String id);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.items_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = list.get(position);
        if (book != null) {
            BookCategoryDAO bookCategoryDAO = new BookCategoryDAO(context);
            BookCategory bookCategory = bookCategoryDAO.getID(String.valueOf(book.getIdBookCategory()));

            holder.tv_name_book.setText("Name Book: " + book.getName());
            holder.tv_rent_book.setText("Rent: " + book.getRent());
            holder.tv_category_book.setText("Category: " + bookCategory.getNameBookCategory());

            holder.tv_id_book.setText("Id:" + book.getIdBook());
            holder.tv_id_book.setEnabled(false);
            holder.tv_id_book.setVisibility(View.GONE);

            holder.img_edit_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickUpdateItem(book);
                }
            });
            holder.img_delete_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickDeleteItem(String.valueOf(book.getIdBook()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class BookViewHolder extends RecyclerView.ViewHolder {
    protected TextView tv_name_book, tv_rent_book, tv_category_book, tv_id_book;
    protected ImageView img_edit_book, img_delete_book;

    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name_book = itemView.findViewById(R.id.tv_name_book);
        tv_rent_book = itemView.findViewById(R.id.tv_rent_book);
        tv_category_book = itemView.findViewById(R.id.tv_category_book);
        tv_id_book = itemView.findViewById(R.id.tv_id_book);
        img_edit_book = itemView.findViewById(R.id.img_edit_book);
        img_delete_book = itemView.findViewById(R.id.img_delete_book);
    }
}