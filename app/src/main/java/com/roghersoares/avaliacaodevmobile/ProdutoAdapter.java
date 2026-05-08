package com.roghersoares.avaliacaodevmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private final List<Produto> produtos;
    private final NumberFormat formatMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public ProdutoAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.textNome.setText(produto.getNomeProduto());
        holder.textCodigo.setText("Código: #" + produto.getCodigoProduto());
        holder.textQuantidade.setText("Qtd: " + produto.getQuantidade());

        try {
            double precoValor = Double.parseDouble(produto.getPreco());
            holder.textPreco.setText(formatMoeda.format(precoValor));
        } catch (Exception e) {
            holder.textPreco.setText(produto.getPreco());
        }
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView textNome, textCodigo, textPreco, textQuantidade;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.textItemNome);
            textCodigo = itemView.findViewById(R.id.textItemCodigo);
            textPreco = itemView.findViewById(R.id.textItemPreco);
            textQuantidade = itemView.findViewById(R.id.textItemQuantidade);
        }
    }
}
