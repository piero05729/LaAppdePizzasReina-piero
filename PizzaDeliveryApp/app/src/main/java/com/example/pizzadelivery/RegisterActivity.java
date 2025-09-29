package com.example.pizzadelivery;

import android.content.Intent;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final TextInputEditText etEmail = findViewById(R.id.etRegEmail);
        final TextInputEditText etPassword = findViewById(R.id.etRegPassword);
        final MaterialButton btnCreate = findViewById(R.id.btnCreateAccount);
        final TextView tvError = findViewById(R.id.tvRegError);

        btnCreate.setOnClickListener(v -> {
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
            btnCreate.setEnabled(false);

            AdminRepositoryImpl repo = new AdminRepositoryImpl();
            repo.register(email, password, new ResultCallback<AdminDto>() {
                @Override
                public void onSuccess(AdminDto data) {
                    runOnUiThread(() -> {
                        btnCreate.setEnabled(true);
                        // Navegar al login o a Main. Aquí regresamos al Login para iniciar sesión.
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    });
                }

                @Override
                public void onError(String message) {
                    runOnUiThread(() -> {
                        btnCreate.setEnabled(true);
                        showError(tvError, message != null ? message : getString(R.string.error_login_failed));
                    });
                }
            });
        });
    }

    private void showError(TextView tvError, String message) {
        tvError.setText(message);
        tvError.setVisibility(View.VISIBLE);
    }
}
