package group3.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import group3.assignment.fragment.BookCategoryFragment;
import group3.assignment.fragment.BookFragment;
import group3.assignment.fragment.CardFragment;
import group3.assignment.fragment.ChangePassFragment;
import group3.assignment.fragment.MemberFragment;
import group3.assignment.fragment.RevenueFragment;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    private View mHeaderView;
    private TextView txtUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);

        //set toolbar
        ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#1761A0"));

        // Set BackgroundDrawable
        ab.setBackgroundDrawable(colorDrawable);
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        //cho nó chạy thành main screen
        FragmentManager manager = getSupportFragmentManager();
        CardFragment cardFragment = new CardFragment();
        manager.beginTransaction().replace(R.id.fl_content, cardFragment)
                .commit();
        //show user in header
        NavigationView nv = findViewById(R.id.nvView);

        mHeaderView = nv.getHeaderView(0);
        txtUser = mHeaderView.findViewById(R.id.txtUser);
        txtUser.setText("Welcome !");

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.nav_Card:
                        setTitle("Card");
                        CardFragment cardFragment = new CardFragment();
                        manager.beginTransaction().replace(R.id.fl_content, cardFragment)
                                .commit();
                        break;
                    case R.id.nav_Book_Category:
                        setTitle("Book Category");
                        BookCategoryFragment bookCategoryFragment = new BookCategoryFragment();
                        manager.beginTransaction()
                                .replace(R.id.fl_content, bookCategoryFragment)
                                .commit();
                        break;
                    case R.id.nav_Book:
                        setTitle("Book");
                        BookFragment bookFragment = new BookFragment();
                        manager.beginTransaction()
                                .replace(R.id.fl_content, bookFragment)
                                .commit();
                        break;
                    case R.id.nav_Member:
                        setTitle("Member");
                        MemberFragment memberFragment = new MemberFragment();
                        manager.beginTransaction()
                                .replace(R.id.fl_content, memberFragment)
                                .commit();
                        break;
                    case R.id.sub_Revenue:
                        setTitle("Revenue");
                        RevenueFragment revenueFragment = new RevenueFragment();
                        manager.beginTransaction()
                                .replace(R.id.fl_content, revenueFragment)
                                .commit();
                        break;

                    case R.id.sub_Pass:
                        setTitle("Change Password");
                        ChangePassFragment changePassFragment = new ChangePassFragment();
                        manager.beginTransaction()
                                .replace(R.id.fl_content, changePassFragment)
                                .commit();
                        break;
                    case R.id.sub_Logout:
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        break;
                }
                drawer.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}