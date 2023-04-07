import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import java.util.ArrayList;

public class FileReader {

    ArrayList<String> nomes = new ArrayList<>();
    int mes;
    int ano;

    // Lendo dados dos funcionários

    public FileReader(File arquivo){
        try {
            Scanner myReader = new Scanner(arquivo);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                nomes.add(line);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ano = Integer.parseInt(nomes.get(nomes.size() - 1));
        nomes.remove(nomes.size() - 1);
        mes = Integer.parseInt(nomes.get(nomes.size() - 1));
        nomes.remove(nomes.size() - 1);
    }

    public int getSize(){
        return nomes.size();
    }
}
