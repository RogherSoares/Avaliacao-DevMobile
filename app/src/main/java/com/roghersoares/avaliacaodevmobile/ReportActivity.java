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

        /*
         * Conexão com o banco de dados
         * 1- Cria uma instância do banco "user-database"
         * 2-.alloeMainThreadQueries(): serve para liberar operações, ROOM proíbe isso.
         * O correto seria fazer consultas em threads separados.
         */

        ProdutoDatabase db = ProdutoDatabase.getInstance(getApplicationContext());
        //Ontém o objeto DAO (Data Access Object) que contém as queries SQL
        ProdutoDao produtoDao = db.produtoDao();
        //Recupera todos os produtos salvos no BD e armazena numa lista
        java.util.List<Produto> produtoList = produtoDao.getAllProdutos();
        //StringBuilder: forma eficiente de construir uma String longa dentro de um laço de repetição
        StringBuilder report = new StringBuilder();
        //Loop "for-each" para percorrer cada objeto Produto na lista
        for (Produto produto : produtoList) {
            report.append("Nome do Produto: ").append(produto.getNomeProduto()).append("\n")
                    .append("Código do Produto: ").append(produto.getCodigoProduto()).append("\n")
                    .append("Preço: ").append(produto.getPreco()).append("\n")
                    .append("Quantidade: ").append(produto.getQuantidade()).append("\n\n");
        }
        //Exibe o relatório no campo de texto
        editTextRelatorio.setText(report.toString());
    }

    //Método para voltar para a tela de cadastro
    private void voltarParaCadastro() {
    //Intenção para abrir a tela de cadastro
        Intent intent = new Intent(ReportActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
