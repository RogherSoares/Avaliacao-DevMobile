package com.roghersoares.avaliacaodevmobile;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Produto {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nomeProduto;
    private String codigoProduto;
    private String preco;
    private String quantidade;

    public Produto(String nomeProduto, String codigoProduto, String preco, String quantidade) {
        this.nomeProduto = nomeProduto;
        this.codigoProduto = codigoProduto;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    // Getters e setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }
    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
