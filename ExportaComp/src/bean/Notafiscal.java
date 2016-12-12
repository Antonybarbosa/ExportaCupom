/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author PHAIMBNOT003
 */
public class Notafiscal {
    private String FilialDestino;
    private String CnpjFonecedor;
    private String DataEntrada;
    private List<Produtos> produtos;
    
    public String getFilialDestino() {
        return FilialDestino;
    }

    public void setFilialDestino(String FilialDestino) {
        this.FilialDestino = FilialDestino;
    }

    public String getCnpjFonecedor() {
        return CnpjFonecedor;
    }

    public void setCnpjFonecedor(String CnpjFonecedor) {
        this.CnpjFonecedor = CnpjFonecedor;
    }

    public String getDataEntrada() {
        return DataEntrada;
    }

    public void setDataEntrada(String DataEntrada) {
        this.DataEntrada = DataEntrada;
    }

    

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }
    
    
}
