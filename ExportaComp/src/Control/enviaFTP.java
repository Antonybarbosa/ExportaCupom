package Control;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class enviaFTP {
    
    
    public  void enviarFTP(String arq) {
        LerPropriedade lp = new LerPropriedade();
        String config[] = lp.Proprertiesler();
        
        String host = config[4];
        String usuario = config[5];
        String senha = config[6];
        
        FTPClient ftp = new FTPClient();

        try {
            System.out.println("Conectando...");

            ftp.connect(host);

            System.out.println("Conectou");

            if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                System.out.println("Autenticando...");
                ftp.login(usuario, senha);
                System.out.println("Autenticou");
                ftp.changeWorkingDirectory(config[7]);

                InputStream is = new FileInputStream("C:/XML/"+arq+".xml");
                File file = new File("C:/XML/"); 
                System.out.println("Enviando Arquivo "
                        + "" + " para "
                        + host);
                ftp.storeFile(arq+".xml", is);
                System.out.println("Workdir >>"
                        + ftp.printWorkingDirectory());
                System.out.println("Arquivo Enviado");
                is.close();
                
                ApagarXML ap = new ApagarXML();
                System.err.println(ap.ApagarXML(file));
                ftp.disconnect();
            } else {
                //erro ao se conectar
                ftp.disconnect();
                System.out.println("Conexão recusada");
                System.exit(0);
            }

        } catch (SocketException ex) {
            Logger.getLogger(enviaFTP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(enviaFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
