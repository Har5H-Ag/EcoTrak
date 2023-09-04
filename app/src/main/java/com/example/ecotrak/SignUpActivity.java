package com.example.ecotrak;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.ecotrak.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FloatingActionButton fabSignUp = findViewById(R.id.fabSignUp);
        ToggleButton toggleButtonMode = findViewById(R.id.toggleButtonMode);
        toggleButtonMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Switch to Login Activity
                Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchLoginSignUp = findViewById(R.id.switchLoginSignUp);
        boolean isDarkModeEnabled = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;

        switchLoginSignUp.setOnCheckedChangeListener((buttonView, isChecked1) -> {
            if (isChecked1) {
                // Enable Dark Mode
                if (!isDarkModeEnabled) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }

            } else {
                // Disable Dark Mode
                if (isDarkModeEnabled)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
        fabSignUp.setOnClickListener(v -> {
            // Perform sign-up logic and validate input
            if (inputIsInvalid()) {
                showValidationErrorDialog();
                Snackbar.make(v, "Invalid input. Please check your details.", Snackbar.LENGTH_LONG).show();

            } else {
                // Proceed with sign-up process
                Toast.makeText(SignUpActivity.this, "Sign-up successful!", Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "Sign-up successful!", Snackbar.LENGTH_LONG).show();
            }
        });



    }

    private boolean inputIsInvalid() {
            EditText editTextName = findViewById(R.id.editTextUsername);
            EditText editTextEmail = findViewById(R.id.editTextEmail);
            Spinner spinnerCountry = findViewById(R.id.spinnerCountry);

            String name = editTextName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String country = spinnerCountry.getSelectedItem().toString();

            if (name.isEmpty() || email.isEmpty() || country.isEmpty()) {
                return true; // Return true if any field is empty
            }

            return false; // All fields are filled
    }

    private void showValidationErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage("Invalid input. Please check your details.")
                .setPositiveButton("OK", null)
                .show();

    }

}
