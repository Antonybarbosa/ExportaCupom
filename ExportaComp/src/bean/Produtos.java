/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.math.BigDecimal;

/**
 *
 * @author PHAIMBNOT003
 */
public class Produtos {
    
    private int CodProduto;
    private String Descricao;
    private BigDecimal QTD;
    private String UND;
    private BigDecimal Valor;

    public int getCodProduto() {
        return CodProduto;
    }

    public void setCodProduto(int CodProduto) {
        this.CodProduto = CodProduto;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public BigDecimal getQTD() {
        return QTD;
    }

    public void setQTD(BigDecimal QTD) {
        this.QTD = QTD;
    }

    public String getUND() {
        return UND;
    }

    public void setUND(String UND) {
        this.UND = UND;
    }

    public BigDecimal getValor() {
        return Valor;
    }

    public void setValor(BigDecimal Valor) {
        this.Valor = Valor;
    }
    

    
    
}
