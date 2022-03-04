package group3.assignment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import group3.assignment.R;
import group3.assignment.fragment.BookCategoryFragment;
import group3.assignment.model.BookCategory;
import group3.assignment.model.Member;

public class BookCategoryAdapter extends RecyclerView.Adapter<BookCategoryViewHolder> {
    private Context context;
    private List<BookCategory> list;
    private IClickListener listener;

    public interface IClickListener {
        void onClickUpdateItem(BookCategory category);

        void onClickDeleteItem(String id);
    }

    public BookCategoryAdapter(Context context, List<BookCategory> list, IClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookCategoryViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.items_book_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookCategoryViewHolder holder, int position) {
        BookCategory category = list.get(position);
        holder.tv_id_book_category.setText("Id:" + category.getIdBookCategory());
        holder.tv_id_book_category.setVisibility(View.GONE);
        holder.tv_name_book_category.setText("Name: " + category.getNameBookCategory());

        holder.img_edit_book_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickUpdateItem(category);
            }
        });

        holder.img_delete_book_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDeleteItem(String.valueOf(category.getIdBookCategory()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class BookCategoryViewHolder extends RecyclerView.ViewHolder {
    protected TextView tv_name_book_category, tv_id_book_category;
    protected ImageView img_edit_book_category, img_delete_book_category;

    public BookCategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name_book_category = itemView.findViewById(R.id.tv_name_book_category);
        tv_id_book_category = itemView.findViewById(R.id.tv_id_book_category);
        img_edit_book_category = itemView.findViewById(R.id.img_edit_book_category);
        img_delete_book_category = itemView.findViewById(R.id.img_delete_book_category);
    }
}
