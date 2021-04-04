package ru.mirea.practice12;

import org.springframework.stereotype.Component;

@Component
public class FileInfo {
    String inputName;
    String outputName;

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }
}
