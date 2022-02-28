package group3.assignment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import group3.assignment.dao.EmployeeDAO;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_username, edt_password;
    private Button btn_login, btn_cancel;
    private CheckBox chk_remember_pass;
    private EmployeeDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#1761A0"));
        // Set BackgroundDrawable
        ab.setBackgroundDrawable(colorDrawable);
        mapping();

        dao = new EmployeeDAO(this);

        //đọc user, pass SharedPreferences, user và pass dc lưu vào file cục bộ
        //của app, có thể lấy nó ra để check remember pass
        SharedPreferences pref = getSharedPreferences("EMP_FILE", MODE_PRIVATE);
        String username = pref.getString("USERNAME", "");
        String password = pref.getString("PASSWORD", "");
        Boolean remember_pass = pref.getBoolean("REMEMBER", false);

        edt_username.setText(username);
        edt_password.setText(password);
        chk_remember_pass.setTag(remember_pass);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_password.setText("");
                edt_username.setText("");
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    private void mapping() {
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        btn_cancel = findViewById(R.id.btn_cancel);
        chk_remember_pass = findViewById(R.id.chk_remember_pass);
    }

    public void rememberUser(String username, String password, boolean status) {
        SharedPreferences pref = getSharedPreferences("EMP_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("USERNAME", username);
            editor.putString("PASSWORD", password);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();
    }

    public void checkLogin() {
        String username = edt_username.getText().toString();
        String password = edt_password.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Not Empty Username or Password", Toast.LENGTH_SHORT).show();
        } else {
            if (dao.checkLogin(username, password) > 0) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                rememberUser(username, password, chk_remember_pass.isChecked());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("user", username);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}