package com.kaya.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_resultado);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8000/api/")
                                                  .addConverterFactory(GsonConverterFactory.create())
                                                  .build();
        Api api = retrofit.create(Api.class);

        Call<List<Evento>> call = api.getEventos();

        call.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Id: " + response.code());
                    return;
                }

                List<Evento> eventos = response.body();

                for (Evento evento: eventos) {
                    String data = "";
                    data += "Id: " + evento.getId() + "\n";
//                    data += "Nome: " + evento.getNome() + "\n";
//                    data += "Descrição: " + evento.getDescricao() + "\n\n";
                    textView.append(data);
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
              textView.setText(t.getMessage());
            }
        });
    }
}
