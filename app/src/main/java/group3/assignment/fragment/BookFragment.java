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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import group3.assignment.R;
import group3.assignment.adapter.BookAdapter;
import group3.assignment.adapter.ListBookCategoryAdapter;
import group3.assignment.dao.BookCategoryDAO;
import group3.assignment.dao.BookDAO;
import group3.assignment.model.Book;
import group3.assignment.model.BookCategory;

public class BookFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private List<Book> listBook;
    private BookAdapter bookAdapter;
    private BookDAO bookDAO;
    //dialog
    private Dialog dialog;
    private EditText edt_id_book, edt_name_book, edt_rent_book;
    private Spinner spinner;
    private Button btn_save_book, btn_cancel_book;
    //spinner
    private ListBookCategoryAdapter listBookCategoryAdapter;
    private List<BookCategory> listBookCategory;
    private BookCategoryDAO bookCategoryDAO;
    private int idBookCategory, position;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book, container, false);
        recyclerView = v.findViewById(R.id.rv_book);
        fab = v.findViewById(R.id.fab);
        bookDAO = new BookDAO(getActivity());
        listBook = new ArrayList<>();
        showBook();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Book book = new Book();
//                openDialog(book, 1);
            }
        });
        return v;
    }

    public void showBook() {
        listBook = bookDAO.getAll();
        bookAdapter = new BookAdapter(getActivity(), listBook, new BookAdapter.IClickListener() {
            @Override
            public void onClickUpdateItem(Book book) {
//                openDialog(book, 0);
            }

            @Override
            public void onClickDeleteItem(String id) {
                delete(id);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(bookAdapter);
    }

    public void delete(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete");
        builder.setMessage("Do you want to delete ?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bookDAO.delete(id);
                showBook();
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

//    private void openDialog(final Book book, final int check) {
//        dialog = new Dialog(getContext());
//        dialog.setContentView(R.layout.dialog_book);
//        edt_id_book = dialog.findViewById(R.id.edt_id_book);
//        edt_id_book.setEnabled(false);
//        edt_id_book.setVisibility(View.GONE);
//        edt_name_book = dialog.findViewById(R.id.edt_name_book);
//        edt_rent_book = dialog.findViewById(R.id.edt_rent_book);
//        spinner = dialog.findViewById(R.id.sp_book_category);
//        btn_save_book = dialog.findViewById(R.id.btn_save_book);
//        btn_cancel_book = dialog.findViewById(R.id.btn_cancel_book);
//        //đổ listBookCategory vào spinner
//        listBookCategory = new ArrayList<>();
//        bookCategoryDAO = new BookCategoryDAO(getActivity());
//        listBookCategory = bookCategoryDAO.getAll();
//        listBookCategoryAdapter = new ListBookCategoryAdapter(getContext(), listBookCategory);
//        spinner.setAdapter(listBookCategoryAdapter);
//        //lấy idBookCategory
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                idBookCategory = listBookCategory.get(position).getIdBookCategory();
//                Toast.makeText(getContext(), "Choose: "
//                        + listBookCategory.get(position).getNameBookCategory(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        if (check != 0) {
//            edt_id_book.setText(String.valueOf(book.getIdBook()));
//            edt_name_book.setText(book.getName());
//            edt_rent_book.setText(String.valueOf(book.getRent()));
//            for (int i = 0; i < listBookCategory.size(); i++) {
//                if (book.getIdBookCategory() == (listBookCategory.get(i).getIdBookCategory())) {
//                    position = i;
//                }
//            }
//            spinner.setSelection(position);
//        }
//
//        btn_cancel_book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        btn_save_book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                book.setName(edt_name_book.getText().toString());
//                book.setRent(Integer.parseInt(edt_rent_book.getText().toString()));
//                book.setIdBookCategory(idBookCategory);
//                if (checkValidate() > 0) {
//                    if (check == 0) {
//                        if (bookDAO.insert(book) > 0) {
//                            Toast.makeText(getContext(), "Insert Successful", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getContext(), "Insert Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        book.setIdBook(Integer.parseInt(edt_id_book.getText().toString()));
//                        if (bookDAO.update(book) > 0) {
//                            Toast.makeText(getContext(), "Insert Successful", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getContext(), "Insert Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//            }
//        });
//    }
//
//    public int checkValidate() {
//        int check = 1;
//        if (edt_name_book.getText().length() == 0 || edt_rent_book.getText().length() == 0) {
//            Toast.makeText(getContext(), "Fill In All Information", Toast.LENGTH_SHORT).show();
//            check = -1;
//        }
//        return check;
//    }
}