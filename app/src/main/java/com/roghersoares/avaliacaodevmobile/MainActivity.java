package com.roghersoares.avaliacaodevmobile;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //Declarando os campos de entrada de dados
    private EditText editTextProduto, editTextCodigoProduto, editTextPreco, editTextQuantidade;
    //Objeto para interação com o banco de dados (DAO)
    private ProdutoDao produtoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Define o layout da atividade


    }
}