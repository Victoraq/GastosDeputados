package GastoDeputados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class LeituraDados {
    
    Deputado[] deputados;
    private int error_count, tam;
    private File file;
    
    public LeituraDados(String nome_arquivo, int tam) {
        this.deputados = new Deputado[tam];
        
        this.error_count = 0;
    
        this.file = new File(nome_arquivo);
        
        this.tam = tam;
    
        this.reader();
    
    }
    
    public void reader() {
        try {
            FileReader fr = new FileReader(this.file);
            BufferedReader br = new BufferedReader(fr);

            String line;

            for (int i = 0; i < this.tam; i++){

                line = br.readLine();
                
                // Separando cada linha lida pela ocorrencia de ;
                String[] position = line.split(";");
                

                // Evitando ler a primeira coluna
                if ("bugged_date".equals(position[0])) {
                    i--;
                    continue;
                }


                Deputado dept = new Deputado(Integer.parseInt(position[0]), position[1], Integer.parseInt(position[2]), position[3],
                                            position[4], position[5], position[6], position[7],
                                            position[8], Float.parseFloat(position[9]));
                deputados[i] = dept;


            }

            System.out.println("Leitura Completa");

        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
