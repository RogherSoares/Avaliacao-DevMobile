package com.roghersoares.avaliacaodevmobile;

import android.os.Bundle;
import android.widget.Button;
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

        //Inicializa os campos de entrada de dados do layout
        editTextProduto = findViewById(R.id.produto);
        editTextCodigoProduto = findViewById(R.id.codigoProduto);
        editTextPreco = findViewById(R.id.preco);
        editTextQuantidade = findViewById(R.id.quantidade);

        //Inicializa os Botões
        Button buttonSalvar = findViewById(R.id.buttonSalvar);
        Button buttonRelatorio = findViewById(R.id.buttonRelatorio);
    }
}