package by.kiselevich.parsingtask.reader;

import by.kiselevich.parsingtask.exception.SourceTextReaderException;

import java.io.*;

public class SourceTextReader {

    private static final String FILE_IS_NULL_EXCEPTION_MESSAGE = "File is null";
    private static final int BUFFER_SIZE = 100;
    private static final int EOF_BYTES_COUNT = -1;

    private File file;

    public SourceTextReader() {

    }

    public SourceTextReader(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getSourceText() throws SourceTextReaderException {
        if (file == null) {
            throw new SourceTextReaderException(FILE_IS_NULL_EXCEPTION_MESSAGE);
        }

        StringBuilder result = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int bytesRead;
            char[] buffer = new char[BUFFER_SIZE];
            while ((bytesRead = br.read(buffer)) != EOF_BYTES_COUNT) {
                result.append(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new SourceTextReaderException(e);
        }

        return result.toString();
    }
}
