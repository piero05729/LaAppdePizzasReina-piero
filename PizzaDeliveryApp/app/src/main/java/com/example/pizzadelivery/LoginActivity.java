package com.example.pizzadelivery;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import com.example.pizzadelivery.core.utils.ResultCallback;
import com.example.pizzadelivery.data.model.AdminDto;
import com.example.pizzadelivery.data.repository.AdminRepositoryImpl;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Setup window insets if needed
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final TextInputEditText etEmail = findViewById(R.id.etEmail);
        final TextInputEditText etPassword = findViewById(R.id.etPassword);
        final MaterialButton btnLogin = findViewById(R.id.btnLogin);
        final MaterialButton btnRegister = findViewById(R.id.btnRegister);
        final TextView tvError = findViewById(R.id.tvError);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
            String password = etPassword.getText() != null ? etPassword.getText().toString() : "";

            if (TextUtils.isEmpty(email)) {
                showError(tvError, getString(R.string.error_empty_usuario));
                return;
            }
            if (TextUtils.isEmpty(password)) {
                showError(tvError, getString(R.string.error_empty_password));
                return;
            }

            tvError.setVisibility(View.GONE);
            btnLogin.setEnabled(false);

            AdminRepositoryImpl repo = new AdminRepositoryImpl();
            repo.login(email, password, new ResultCallback<AdminDto>() {
                @Override
                public void onSuccess(AdminDto data) {
                    runOnUiThread(() -> {
                        btnLogin.setEnabled(true);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    });
                }

                @Override
                public void onError(String message) {
                    runOnUiThread(() -> {
                        btnLogin.setEnabled(true);
                        showError(tvError, message != null ? message : getString(R.string.error_login_failed));
                    });
                }
            });
        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void showError(TextView tvError, String message) {
        tvError.setText(message);
        tvError.setVisibility(View.VISIBLE);
    }
}