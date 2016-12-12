/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.CreateFolder;
import Control.GetDateTime;
import Control.LerPropriedade;
import Control.enviaFTP;
import bean.Notafiscal;
import bean.Produtos;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author PHAIMBNOT003
 */
public class Conexao extends JFrame {

    public List<Produtos> listapro = new ArrayList<Produtos>();

    public Connection con = null;

    int[] cdfil = {1, 2, 3, 5, 8, 12, 14, 15, 16, 17};

    String cdfilTotvs[] = {"0201", "0204", "0206", "0209", "0207", "0205", "0213", "0210", "0211", "0212"};

    String Data = null, retorno = "OK";

    LerPropriedade lp = new LerPropriedade();

    public Conexao() {
        String config[] = lp.Proprertiesler();
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");

            con = DriverManager.getConnection("jdbc:firebirdsql:" + config[0] + "/3050:" + config[3],
                    config[1],
                    config[2]);

            con.setAutoCommit(false);

            System.out.println("Conexão OK!");
            retorno = "OK";

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao se conectar");
            retorno = "ERRO";
        }

    }

    //vamos consultar as requisição de cada cupom
    public void consultarq() {

        GetDateTime dt = new GetDateTime();
        String arq;
        Data = dt.getDateTime();

        String SQL = "select distinct  a.cdprin, c.descr, sum(a.qtrealuni) as qtd, c.unida, sum(c.prcom) as valor"
                + " from fc12110 a left join fc05000 b on (a.cdprin = b.cdsac), fc03000 c where b.cdsac is null and"
                + " a.cdprin = c.cdpro and a.cdfil = ? and dtentr = '" + Data + "' group by 1,2,4";

        PreparedStatement ps;
        ResultSet rs = null;

        Data = dt.DateTime();

        try {
            ps = con.prepareStatement(SQL, rs.CONCUR_READ_ONLY, rs.TYPE_SCROLL_INSENSITIVE);
            for (int t = 0; t < cdfil.length; t++) {
                Notafiscal nf = new Notafiscal();
                ps.setInt(1, cdfil[t]);
                rs = ps.executeQuery();

                if (!rs.next()) {

                    System.err.println("Filial " + cdfil[t] + " Não funcionou!");

                } else {
                    int filial = 0, filial2 = 0;
                    while (rs.next()) {
                        filial2 = cdfil[t];
                        if (filial == 0) {

                            nf.setFilialDestino(cdfilTotvs[t]);
                            nf.setCnpjFonecedor("12806626000250");
                            nf.setDataEntrada(Data);
                            filial = filial2;

                        }

                        Produtos pd = new Produtos();
                        pd.setCodProduto(rs.getInt("cdprin"));
                        pd.setDescricao(rs.getString("descr"));
                        pd.setQTD(rs.getBigDecimal("qtd"));
                        pd.setUND(rs.getString("unida"));
                        pd.setValor(rs.getBigDecimal("valor"));
                        listapro.add(pd);

                    }
                    nf.setProdutos(listapro);

                    XStream xstream = new XStream(new DomDriver());
                    xstream.alias("NotaFiscal", Notafiscal.class);
                    xstream.alias("Iten", Produtos.class);
                    String xml = xstream.toXML(nf);
                    System.out.println(xml);
                    listapro.clear();

                    arq = cdfilTotvs[t] + Data;
                    salvarXml(xml, arq);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String consultArq(String data) {

        Data = data;
        new Thread() {

            public void run() {

                String arq;

                String SQL = "select distinct  a.cdprin, c.descr, sum(a.qtrealuni) as qtd, c.unida, sum(c.prcom) as valor"
                        + " from fc12110 a left join fc05000 b on (a.cdprin = b.cdsac), fc03000 c where b.cdsac is null and"
                        + " a.cdprin = c.cdpro and a.cdfil = ? and dtentr = '" + Data + "' group by 1,2,4";

                PreparedStatement ps;
                ResultSet rs = null;

                try {
                    ps = con.prepareStatement(SQL, rs.CONCUR_READ_ONLY, rs.TYPE_SCROLL_INSENSITIVE);
                    for (int t = 0; t < cdfil.length; t++) {
                        Notafiscal nf = new Notafiscal();
                        ps.setInt(1, cdfil[t]);
                        rs = ps.executeQuery();

                        if (!rs.next()) {

                            System.err.println("Filial " + cdfil[t] + " Não funcionou!");

                        } else {

                            int filial = 0, filial2 = 0;

                            do {
                                filial2 = cdfil[t];
                                if (filial == 0) {

                                    nf.setFilialDestino(cdfilTotvs[t]);
                                    nf.setCnpjFonecedor("12806626000250");
                                    nf.setDataEntrada(Data);
                                    filial = filial2;

                                }

                                Produtos pd = new Produtos();
                                pd.setCodProduto(rs.getInt("cdprin"));
                                pd.setDescricao(rs.getString("descr"));
                                pd.setQTD(rs.getBigDecimal("qtd"));
                                pd.setUND(rs.getString("unida"));
                                pd.setValor(rs.getBigDecimal("valor"));
                                listapro.add(pd);

                            } while (rs.next());

                            nf.setProdutos(listapro);

                            XStream xstream = new XStream(new DomDriver());
                            xstream.alias("NotaFiscal", Notafiscal.class);
                            xstream.alias("Iten", Produtos.class);
                            String xml = xstream.toXML(nf);
                            System.out.println(xml);
                            listapro.clear();

                            Data = Data.replace(".", "");
                            arq = cdfilTotvs[t] + Data;
                            salvarXml(xml, arq);

                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }.start();

        return retorno;

    }

    public void salvarXml(String xml, String arquivo) {
        
        CreateFolder cf = new CreateFolder();
        cf.createFolder();
        
        PrintWriter print = null;

        File file = new File("C:\\xml\\" + arquivo + ".xml");
        try {
            print = new PrintWriter(file);
            print.write(xml);
            print.flush();
            print.close();
            file = new File("");
            enviaFTP ftp = new enviaFTP();
            ftp.enviarFTP(arquivo);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
