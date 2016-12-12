/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.net.MalformedURLException;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class CommonsMail {
	public CommonsMail(String msg) throws EmailException, MalformedURLException {
		enviaEmailSimples(msg);
	}
	/**
	 * Classe que envia E-amil
	 * @throws EmailException
	 */
	public void enviaEmailSimples(String Msg) throws EmailException {
		
                Email email = new SimpleEmail();
                email.setDebug(true);
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		//email.setHostName("smtp.pharmapele.com.br"); // o servidor SMTP para envio do e-mail
		email.setSmtpPort(587);
                email.setSSLOnConnect(true);
                email.setStartTLSEnabled(true);
                email.setAuthentication("softwaredeveloperantony@gmail.com", "tony#020567");
		//email.setAuthentication("antony@pharmapele.com.br", "tony#020567");
		
                //email.setFrom("softwaredeveloperantony@gmail.com"); // remetente
                email.setFrom("antony@pharmapele.com.br"); // remetente
                email.setSubject("Exporta Estoque lojas"); // assunto do e-mail
	        email.setMsg(
                                 Msg ); //conteudo do e-mail
	        email.addTo("antony@pharmapele.com.br","Antony"); //destinatário
		
		//email.sets(true);
		//email.setTLS(true);
                try{
		
                    email.send();
                    
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
	}
        
}