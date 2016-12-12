/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.File;

/**
 *
 * @author PHAIMBNOT003
 */
public class ApagarXML {
    
    String status;
    
    public String ApagarXML(File f){
        
        
        // Se o arquivo passado for um diretório
        if (f.isDirectory()) {
                /* Lista todos os arquivos do diretório em um array 
                   de objetos File */
                File[] files = f.listFiles();
                // Identa a lista (foreach) e deleta um por um
                for (File file : files) {
                        file.delete();
                }
                status = "Arquivo Excluido!";
        }
        else{
            status = "Arquivo não encontrado";
        }
        
        return status;
    }
}
