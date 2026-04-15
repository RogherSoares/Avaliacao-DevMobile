package com.roghersoares.avaliacaodevmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {
    //Campo de texto para exibir os produtos cadastrados
    private EditText editTextRelatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report); // Define o layout da atividade
        //Mapeamento do TextView do XML para o Java
        editTextRelatorio = findViewById(R.id.editTextRelatorio);
        //encontra o botão e define o clique para voltar
        Button btnVoltar = findViewById(R.id.btnVoltar);
        //O botão de retorno utilizando expressão lambda
        btnVoltar.setOnClickListener(v -> voltarParaCadastro());
    }

    //Método para voltar para a tela de cadastro
    private void voltarParaCadastro() {
    //Intenção para abrir a tela de cadastro
        Intent intent = new Intent(ReportActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
