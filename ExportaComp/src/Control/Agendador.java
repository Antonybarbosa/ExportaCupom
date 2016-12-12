/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Conexao;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


 

    /**
     * Classe para manipular a execução de tarefas agendadas automaticamentes
     * @author Jean C Becker

     * @version 1.0
     */

public class Agendador {

 

    Timer timer;

    /**

     * Método para iniciar a execução das tarefas.

     */

    public void iniciar() {

 

        timer = new Timer();

        //Executa tarefa a cada 24 horas a partir da primeira

        //       timer.schedule(new tarefasDiarias(),

        //        0,

        //        1 * 1000 * 60 * 60 * 24);

 

        //Executa tarefa todo dia as 6 da manha

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 23);

        calendar.set(Calendar.MINUTE, 0);

        calendar.set(Calendar.SECOND, 0);

        Date time = calendar.getTime();

        timer.schedule(new tarefasDiarias(), time);

    }

    /**

     * Método para interromper a execução das tarefas.

     */

    public void parar() {

        timer.cancel();

    }

    /**

     * Método que contém as tarefas agendadas que serão executadas.

     */

    class tarefasDiarias extends TimerTask {

 

       public void run() {

          //Aqui ficam as tarefas que vão ser executadas...
          Conexao cn = new Conexao();
                cn.consultarq();
        }

    }

}
