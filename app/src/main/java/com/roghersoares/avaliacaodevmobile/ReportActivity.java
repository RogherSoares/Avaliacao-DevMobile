package com.roghersoares.avaliacaodevmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProdutos);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        // Configura o clique para voltar
        btnVoltar.setOnClickListener(v -> voltarParaCadastro());

        // Inicializa o banco de dados e recupera a lista
        ProdutoDatabase db = ProdutoDatabase.getInstance(getApplicationContext());
        ProdutoDao produtoDao = db.produtoDao();
        List<Produto> produtoList = produtoDao.getAllProdutos();

        // Configura o RecyclerView
        if (produtoList != null && !produtoList.isEmpty()) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ProdutoAdapter adapter = new ProdutoAdapter(produtoList);
            recyclerView.setAdapter(adapter);
        } else {
            // Se não houver produtos, podemos mostrar uma mensagem (opcional, já que o RecyclerView ficaria vazio)
            // No layout original tínhamos um TextView para isso, podemos adicionar se necessário
        }
    }

    private void voltarParaCadastro() {
        Intent intent = new Intent(ReportActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
