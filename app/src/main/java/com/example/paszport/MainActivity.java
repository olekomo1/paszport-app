package com.example.paszport; // <-- Zmień na nazwę swojego pakietu!

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PASZPORT_LOG";

    private EditText etNumer, etImie, etNazwisko;
    private RadioGroup rgKolorOczu;
    private ImageView ivZdjecie, ivOdcisk;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja widoków
        etNumer = findViewById(R.id.etNumer);
        etImie = findViewById(R.id.etImie);
        etNazwisko = findViewById(R.id.etNazwisko);
        rgKolorOczu = findViewById(R.id.rgKolorOczu);
        ivZdjecie = findViewById(R.id.ivZdjecie);
        ivOdcisk = findViewById(R.id.ivOdcisk);
        btnOk = findViewById(R.id.btnOk);

        // Zdarzenie opuszczenia pola Numer (odpowiednik FocusLost / Leave z desktopa)
        etNumer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    aktualizujZdjecia();
                }
            }
        });

        // Obsługa przycisku OK
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Odświeżamy też zdjęcia na wypadek, gdyby użytkownik nie opuścił pola przed kliknięciem OK
                aktualizujZdjecia();

                String imie = etImie.getText().toString().trim();
                String nazwisko = etNazwisko.getText().toString().trim();

                if (imie.isEmpty() || nazwisko.isEmpty()) {
                    String blad = "Wprowadź dane";
                    Toast.makeText(MainActivity.this, blad, Toast.LENGTH_SHORT).show();
                    Log.w(TAG, "Błąd walidacji: " + blad);
                    return;
                }

                // Pobranie zaznaczonego koloru oczu
                int selectedId = rgKolorOczu.getCheckedRadioButtonId();
                RadioButton wybranyRadio = findViewById(selectedId);
                String kolorOczu = wybranyRadio.getText().toString();

                String komunikat = imie + " " + nazwisko + " kolor oczu " + kolorOczu;

                // Wyświetlenie Toast oraz wpis do Logcat
                Toast.makeText(MainActivity.this, komunikat, Toast.LENGTH_LONG).show();
                Log.d(TAG, "Zatwierdzono dane: " + komunikat);
            }
        });
    }

    private void aktualizujZdjecia() {
        String numer = etNumer.getText().toString().trim();

        switch (numer) {
            case "000":
                ivZdjecie.setImageResource(R.drawable.zdjecie_000);
                ivOdcisk.setImageResource(R.drawable.odcisk_000);
                break;
            case "111":
                ivZdjecie.setImageResource(R.drawable.zdjecie_111);
                ivOdcisk.setImageResource(R.drawable.odcisk_111);
                break;
            case "333":
                ivZdjecie.setImageResource(R.drawable.zdjecie_333);
                ivOdcisk.setImageResource(R.drawable.odcisk_333);
                break;
            default:
                ivZdjecie.setImageDrawable(null);
                ivOdcisk.setImageDrawable(null);
                break;
        }
    }
}