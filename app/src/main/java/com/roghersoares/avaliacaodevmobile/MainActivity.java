package com.roghersoares.avaliacaodevmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    // Declarando os campos de entrada de dados
    private EditText editTextProduto, editTextCodigoProduto, editTextPreco, editTextQuantidade;
    // Objeto para interacao com o banco de dados (DAO)
    private ProdutoDao produtoDao;
    private static final Pattern PRECO_PATTERN = Pattern.compile("^\\d+(\\.\\d{1,2})?$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Define o layout da atividade

        // Inicializa os campos de entrada de dados do layout
        editTextProduto = findViewById(R.id.produto);
        editTextCodigoProduto = findViewById(R.id.codigoProduto);
        editTextPreco = findViewById(R.id.preco);
        editTextQuantidade = findViewById(R.id.quantidade);

        // Inicializa os botoes
        Button buttonSalvar = findViewById(R.id.btnSalvar);
        Button buttonRelatorio = findViewById(R.id.btnRelatorio);

        // Configuracao do banco de dados com Room
        ProdutoDatabase db = ProdutoDatabase.getInstance(getApplicationContext());
        produtoDao = db.produtoDao();

        // Configura o botao de salvar produto
        buttonSalvar.setOnClickListener(v -> {
            Log.d("MainActivity", "Botao Salvar clicado");

            String nomeProduto = editTextProduto.getText().toString().trim();
            String codigoProduto = editTextCodigoProduto.getText().toString().trim();
            String preco = editTextPreco.getText().toString().trim();
            String quantidade = editTextQuantidade.getText().toString().trim();

            if (nomeProduto.isEmpty() || codigoProduto.isEmpty() || preco.isEmpty() || quantidade.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!PRECO_PATTERN.matcher(preco).matches()) {
                Toast.makeText(MainActivity.this, "Preco invalido. Use numero positivo com ate 2 casas decimais", Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal valorPreco = new BigDecimal(preco);
            if (valorPreco.compareTo(BigDecimal.ZERO) <= 0) {
                Toast.makeText(MainActivity.this, "Preco deve ser maior que zero", Toast.LENGTH_SHORT).show();
                return;
            }

            int valorQuantidade;
            try {
                valorQuantidade = Integer.parseInt(quantidade);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Quantidade invalida. Use numero inteiro positivo", Toast.LENGTH_SHORT).show();
                return;
            }

            if (valorQuantidade <= 0) {
                Toast.makeText(MainActivity.this, "Quantidade deve ser maior que zero", Toast.LENGTH_SHORT).show();
                return;
            }

            Produto produto = new Produto(nomeProduto, codigoProduto, preco, String.valueOf(valorQuantidade));
            produtoDao.insert(produto);
            Toast.makeText(MainActivity.this, "Produto inserido com sucesso", Toast.LENGTH_SHORT).show();
            Log.d("MainActivity", "Produto inserido com sucesso");

            editTextProduto.setText("");
            editTextCodigoProduto.setText("");
            editTextPreco.setText("");
            editTextQuantidade.setText("");
        });

        // Configura o botao de relatorio para exibir os produtos cadastrados
        buttonRelatorio.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ReportActivity.class)));
    }
}