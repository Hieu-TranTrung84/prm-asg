package group3.assignment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import group3.assignment.R;
import group3.assignment.dao.BookDAO;
import group3.assignment.dao.MemberDAO;
import group3.assignment.model.Book;
import group3.assignment.model.Card;
import group3.assignment.model.Member;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private Context context;
    private List<Card> list;
    private IClickListener listener;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private BookDAO bookDAO;
    private MemberDAO memberDAO;

    public CardAdapter(Context context, List<Card> list, IClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public interface IClickListener {
        void onClickUpdateItem(Card card);

        void onClickDeleteItem(String id);
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.items_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = list.get(position);

        holder.tv_id_card.setText("Id: " + card.getIdCard());
        holder.tv_id_card.setEnabled(false);
        holder.tv_id_card.setVisibility(View.GONE);

        bookDAO = new BookDAO(context);
        Book book = bookDAO.getId(String.valueOf(card.getIdBook()));
        holder.tv_name_book_card.setText("Name Book: " + book.getName());

        memberDAO = new MemberDAO(context);
        Member member = memberDAO.getId(String.valueOf(card.getIdMember()));
        holder.tv_name_member_card.setText("Name Member: " + member.getName());

        holder.tv_price_card.setText("Price: " + card.getPrice());

        holder.tv_date_card.setText("Date: " + sdf.format(card.getDate()));

        if (card.getReturnBook() == 1) {
            holder.tv_returnBook_card.setTextColor(Color.BLUE);
            holder.tv_returnBook_card.setText("Returned");
        } else {
            holder.tv_returnBook_card.setTextColor(Color.RED);
            holder.tv_returnBook_card.setText("Not Returned");
        }
        holder.img_edit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickUpdateItem(card);
            }
        });
        holder.img_delete_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDeleteItem(String.valueOf(card.getIdCard()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CardViewHolder extends RecyclerView.ViewHolder {
    protected TextView tv_id_card, tv_name_member_card, tv_name_book_card,
            tv_returnBook_card, tv_price_card, tv_date_card;
    protected ImageView img_edit_card, img_delete_card;

    public CardViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_id_card = itemView.findViewById(R.id.tv_id_card);
        tv_name_member_card = itemView.findViewById(R.id.tv_name_member_card);
        tv_name_book_card = itemView.findViewById(R.id.tv_name_book_card);
        tv_returnBook_card = itemView.findViewById(R.id.tv_returnBook_card);
        tv_price_card = itemView.findViewById(R.id.tv_price_card);
        tv_date_card = itemView.findViewById(R.id.tv_date_card);
        img_edit_card = itemView.findViewById(R.id.img_edit_card);
        img_delete_card = itemView.findViewById(R.id.img_delete_card);
    }
}