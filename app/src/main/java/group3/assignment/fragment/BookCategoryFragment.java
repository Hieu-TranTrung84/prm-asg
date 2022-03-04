package group3.assignment.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import group3.assignment.adapter.BookCategoryAdapter;
import group3.assignment.dao.BookCategoryDAO;
import group3.assignment.model.BookCategory;


public class BookCategoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookCategoryDAO dao;
    private FloatingActionButton fab;
    private List<BookCategory> list;
    private BookCategoryAdapter adapter;

    private Dialog dialog;
    private EditText edt_id_book_category, edt_name_book_category;
    private Button btn_save_book_category, btn_cancel_book_category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_book_category, container, false);
        recyclerView = v.findViewById(R.id.rv_book_category);
        dao = new BookCategoryDAO(getActivity());
        fab = v.findViewById(R.id.fab);
        list = new ArrayList<>();

        showBookCategory();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookCategory category = new BookCategory();
                openDialog(category, 0);
            }
        });

        return v;
    }

    private void showBookCategory() {
        list = dao.getAll();
        adapter = new BookCategoryAdapter(getActivity(), list, new BookCategoryAdapter.IClickListener() {
            @Override
            public void onClickUpdateItem(BookCategory category) {
                openDialog(category, 1);
            }

            @Override
            public void onClickDeleteItem(String id) {
                delete(id);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    public void delete(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Do you want to delete ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                showBookCategory();
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

    public void openDialog(final BookCategory category, final int check) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_book_category);

        edt_id_book_category = dialog.findViewById(R.id.edt_id_book_category);
        edt_id_book_category.setEnabled(false);
        edt_id_book_category.setVisibility(View.GONE);
        edt_name_book_category = dialog.findViewById(R.id.edt_name_book_category);

        btn_cancel_book_category = dialog.findViewById(R.id.btn_cancel_book_category);
        btn_save_book_category = dialog.findViewById(R.id.btn_save_book_category);


        if (check != 0) {
            edt_id_book_category.setText(String.valueOf(category.getIdBookCategory()));
            edt_name_book_category.setText(category.getNameBookCategory());
        }

        btn_cancel_book_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save_book_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidate() > 0) {
                    category.setNameBookCategory(edt_name_book_category.getText().toString());
                    if (check == 0) {
                        if (dao.insert(category) > 0) {
                            Toast.makeText(getContext(), "Insert Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Insert Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        category.setIdBookCategory(Integer.parseInt(edt_id_book_category.getText().toString()));
                        if (dao.update(category) > 0) {
                            Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    showBookCategory();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int checkValidate() {
        int check = 1;
        if (edt_name_book_category.getText().length() == 0) {
            Toast.makeText(getContext(), "Fill In All Information", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}