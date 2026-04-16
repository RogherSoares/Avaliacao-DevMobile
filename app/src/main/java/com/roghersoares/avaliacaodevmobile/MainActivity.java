package com.roghersoares.avaliacaodevmobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    // Declarando os campos de entrada de dados
    private EditText editTextProduto, editTextCodigoProduto, editTextPreco, editTextQuantidade;
    // Objeto para interacao com o banco de dados (DAO)
    private ProdutoDao produtoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Define o layout da atividade

        // Inicializa os campos de entrada de dados do layout
        editTextProduto = findViewById(R.id.produto);
        editTextCodigoProduto = findViewById(R.id.codigoProduto);
        editTextPreco = findViewById(R.id.preco);
        editTextQuantidade = findViewById(R.id.quantidade);

        // Adiciona formatação de dinheiro ao campo de preço (100,00)
        setupPrecoFormatter();

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
            String precoFormatado = editTextPreco.getText().toString().trim();
            String quantidade = editTextQuantidade.getText().toString().trim();

            if (nomeProduto.isEmpty() || codigoProduto.isEmpty() || precoFormatado.isEmpty() || quantidade.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Converte o preço formatado (ex: 100,00) para o formato numérico (ex: 100.00) para salvar no BD
            String precoLimpo = precoFormatado.replace(".", "").replace(",", ".");
            BigDecimal valorPreco;
            try {
                valorPreco = new BigDecimal(precoLimpo);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Preço inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            if (valorPreco.compareTo(BigDecimal.ZERO) <= 0) {
                Toast.makeText(MainActivity.this, "Preço deve ser maior que zero", Toast.LENGTH_SHORT).show();
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

            // Salvamos no banco o valor com ponto decimal para facilitar a formatação posterior
            Produto produto = new Produto(nomeProduto, codigoProduto, valorPreco.toString(), String.valueOf(valorQuantidade));
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

    private void setupPrecoFormatter() {
        editTextPreco.addTextChangedListener(new TextWatcher() {
            private String current = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    editTextPreco.removeTextChangedListener(this);

                    // Remove tudo que não é dígito
                    String cleanString = s.toString().replaceAll("[^\\d]", "");

                    if (!cleanString.isEmpty()) {
                        try {
                            double parsed = Double.parseDouble(cleanString) / 100;
                            // Formata como 1.234,56 (sem o R$)
                            current = String.format(new Locale("pt", "BR"), "%,.2f", parsed);
                            editTextPreco.setText(current);
                            editTextPreco.setSelection(current.length());
                        } catch (NumberFormatException e) {
                            Log.e("MainActivity", "Erro ao formatar preço", e);
                        }
                    } else {
                        current = "";
                        editTextPreco.setText("");
                    }

                    editTextPreco.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}