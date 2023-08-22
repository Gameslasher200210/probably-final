package todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class fileActions {
        public void add(String fileName, String contentToAdd) {
        try {
            // Step 1: Open the file in append mode using FileWriter and BufferedWriter
            FileWriter fileWriter = new FileWriter(fileName, true); // true indicates append mode
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Step 2: Write the content to the file
            bufferedWriter.write(contentToAdd);
            bufferedWriter.newLine(); // Optional: Add a new line after the content

            // Step 3: Close the file to save changes
            bufferedWriter.close();

            System.out.println("Content has been added to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
       public void removeByLineNumber(String fileName, int lineNumberToRemove) {
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            int currentLineNumber = 1;

            while ((currentLine = reader.readLine()) != null) {
                // Check if the current line number matches the specified line number to remove
                if (currentLineNumber != lineNumberToRemove) {
                    writer.write(currentLine);
                    writer.newLine();
                }
                currentLineNumber++;
            }

            writer.close();
            reader.close();

            // Delete the original file
            if (!inputFile.delete()) {
                System.err.println("Error deleting the original file.");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Error renaming the temporary file.");
            }

            System.out.println("Line removed successfully.");
        } catch (IOException e) {
            System.err.println("Error removing line from the file: " + e.getMessage());
        }
    }
    

    public void update(String fileName, String newContent, int lineToUpdate) {
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("temp.txt");
    
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    
            String currentLine;
            int currentLineNum = 1;
    
            while ((currentLine = reader.readLine()) != null) {
                if (currentLineNum == lineToUpdate) {
                    writer.write(newContent);
                    writer.newLine(); // Add a new line to preserve the line endings
                } else {
                    writer.write(currentLine);
                    writer.newLine(); // Add a new line to preserve the line endings
                }
                currentLineNum++;
            }
            
            writer.close();
            reader.close();
    
            // Delete the original file
            if (!inputFile.delete()) {
                System.err.println("Error deleting the original file.");
                return;
            }
    
            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Error renaming the temporary file.");
            }
    
            System.out.println("Line updated successfully.");
        } catch (IOException e) {
            System.err.println("Error updating line in the file: " + e.getMessage());
        }
    }
    

}
