/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LeituraDados {
    
    List<Deputado> deputados;
    int error_count;
    File file;
    
    public LeituraDados(String nome_arquivo) {
        
        this.deputados = new ArrayList<>();
        
        this.error_count = 0;
    
        this.file = new File(nome_arquivo);
    
        this.reader();
    
    }
    
    private void reader() {
        try {
            FileReader fr = new FileReader(this.file);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while( ( line = br.readLine() ) != null){
            //for (int i = 0; i < 10; i++){

                //line = br.readLine();

                String[] position = line.split(",");

                // Evitando ler a primeira coluna
                if ("bugged_date".equals(position[0])) {
                    continue;
                }

                System.out.println(position[9]);

                try {
                    Deputado dept = new Deputado(Integer.parseInt(position[0]), position[1], Integer.parseInt(position[2]), position[3],
                                            position[4], position[5], position[6], position[7],
                                            position[8], Float.parseFloat(position[9]));
                    deputados.add(dept);
                
                // Problema a ser resolvido: virgula no meio de strings
                } catch(NumberFormatException e) {
                    System.out.println(e.getMessage());
                    System.out.println(line);
                    this.error_count++;
                }


                //System.out.println(deputados.get(i-1).name);

            }

            System.out.println("Leitura Completa");

        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}