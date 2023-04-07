import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

    public static File main() {
        JFileChooser fileChooser = new JFileChooser();
        
        // Set the current directory of the file chooser
        fileChooser.setCurrentDirectory(new java.io.File("."));
        
        // Set the file filter to display only text files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.setFileFilter(filter);
        
        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            java.io.File selectedFile = fileChooser.getSelectedFile();
            
            // Do something with the selected file
            return selectedFile;
        }
        return null;
    }
}