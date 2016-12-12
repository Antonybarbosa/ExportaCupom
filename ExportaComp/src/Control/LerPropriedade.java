/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author PHAIMBNOT003
 */
public class LerPropriedade {

    public String[] Proprertiesler() {
            
        Properties prop = new Properties();
        String dados[] = new String[8];
        try {
            prop.load(new FileInputStream("./config.properties"));
            
            dados[0] = prop.getProperty("prop.server.srv");
            dados[1] = prop.getProperty("prop.server.login");
            dados[2] = prop.getProperty("prop.server.password");
            dados[3] = prop.getProperty("prop.server.bdpasta");
            dados[4] = prop.getProperty("prop.server.ftpserver");
            dados[5] = prop.getProperty("prop.server.ftplogin");
            dados[6] = prop.getProperty("prop.server.ftppassword");
            dados[7] = prop.getProperty("prop.server.ftprepositorio");
             
            
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Arquivo de configuração /nde não encontrado");
            Logger.getLogger(LerPropriedade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dados;
    }
    
}
