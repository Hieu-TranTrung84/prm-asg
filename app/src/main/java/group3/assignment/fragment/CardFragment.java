package group3.assignment.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import group3.assignment.R;
import group3.assignment.adapter.CardAdapter;
import group3.assignment.adapter.ListBookAdapter;
import group3.assignment.adapter.ListMemberAdapter;
import group3.assignment.dao.BookDAO;
import group3.assignment.dao.CardDAO;
import group3.assignment.dao.MemberDAO;
import group3.assignment.model.Book;
import group3.assignment.model.Card;
import group3.assignment.model.Member;


public class CardFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private List<Card> listCard;
    private CardAdapter cardAdapter;
    private CardDAO cardDAO;
    //dialog
    private Dialog dialog;
    private EditText edt_id_card;
    private Spinner sp_member, sp_book;
    private TextView tv_date_card_dialog, tv_price_card_dialog;
    private Button btn_save_card, btn_cancel_card;
    private CheckBox chk_return_book;
    //list member
    private List<Member> listMember;
    private Member member;
    private MemberDAO memberDAO;
    private ListMemberAdapter listMemberAdapter;
    private int idMember;
    //list book
    private List<Book> listBook;
    private Book book;
    private BookDAO bookDAO;
    private ListBookAdapter listBookAdapter;
    private int idBook, price;
    private int positionMember, positionBook;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card, container, false);
        recyclerView = v.findViewById(R.id.rv_card);
        fab = v.findViewById(R.id.fab);
        listCard = new ArrayList<>();
        cardDAO = new CardDAO(getActivity());

        showCard();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card card = new Card();
                openDialog(card, 0);
            }
        });
        return v;
    }

    public void showCard() {
        listCard = cardDAO.getAll();
        cardAdapter = new CardAdapter(getActivity(), listCard, new CardAdapter.IClickListener() {
            @Override
            public void onClickUpdateItem(Card card) {
                openDialog(card, 1);
            }

            @Override
            public void onClickDeleteItem(String id) {
                delete(id);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cardAdapter);
    }

    public void delete(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete");
        builder.setMessage("Do you want to delete ?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cardDAO.delete(id);
                showCard();
                dialog.dismiss();
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

    public void openDialog(final Card card, final int check) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_card);
        edt_id_card = dialog.findViewById(R.id.edt_id_card);
        edt_id_card.setEnabled(false);
        edt_id_card.setVisibility(View.GONE);
        sp_member = dialog.findViewById(R.id.sp_member);
        sp_book = dialog.findViewById(R.id.sp_book);
        tv_date_card_dialog = dialog.findViewById(R.id.tv_date_card_dialog);
        tv_date_card_dialog.setText("Date: " + sdf.format(new Date()));
        tv_price_card_dialog = dialog.findViewById(R.id.tv_price_card_dialog);
        btn_cancel_card = dialog.findViewById(R.id.btn_cancel_card);
        chk_return_book = dialog.findViewById(R.id.chk_return_book);
        btn_save_card = dialog.findViewById(R.id.btn_save_card);

        //đổ listMember vào spinner member
        memberDAO = new MemberDAO(getActivity());
        listMember = new ArrayList<>();
        listMember = memberDAO.getAll();
        listMemberAdapter = new ListMemberAdapter(getActivity(), listMember);
        sp_member.setAdapter(listMemberAdapter);
        sp_member.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idMember = listMember.get(position).getIdMember();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //đổ listBook vào spinner book
        bookDAO = new BookDAO(getActivity());
        listBook = new ArrayList<>();
        listBook = bookDAO.getAll();
        listBookAdapter = new ListBookAdapter(getActivity(), listBook);
        sp_book.setAdapter(listBookAdapter);
        sp_book.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idBook = listBook.get(position).getIdBook();
                price = listBook.get(position).getRent();
                tv_price_card_dialog.setText("Price: " + price);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //edit: set data lên form dialog
        if (check != 0) {
            edt_id_card.setText(String.valueOf(card.getIdCard()));
            for (int i = 0; i < listMember.size(); i++) {
                if (card.getIdMember() == listMember.get(i).getIdMember())
                    positionMember = i;
            }
            sp_member.setSelection(positionMember);

            for (int i = 0; i < listBook.size(); i++) {
                if (card.getIdBook() == listBook.get(i).getIdBook())
                    positionBook = i;
            }
            sp_book.setSelection(positionBook);

            tv_date_card_dialog.setText("Date: " + sdf.format(card.getDate()));
            tv_price_card_dialog.setText("Price: " + card.getPrice());
            if (card.getReturnBook() == 1) {
                chk_return_book.setChecked(true);
            } else {
                chk_return_book.setChecked(false);
            }
        }

        btn_cancel_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.setIdBook(idBook);
                card.setIdMember(idMember);
                card.setDate(new Date());
                card.setPrice(price);
                if (chk_return_book.isChecked()) {
                    card.setReturnBook(1);
                } else {
                    card.setReturnBook(0);
                }
                //check insert or update. insert == 0, update == 1
                if (check == 0) {
                    if (cardDAO.insert(card) > 0) {
                        Toast.makeText(getContext(), "Insert Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Insert Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    card.setIdCard(Integer.parseInt(edt_id_card.getText().toString()));
                    if (cardDAO.update(card) > 0) {
                        Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                showCard();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}