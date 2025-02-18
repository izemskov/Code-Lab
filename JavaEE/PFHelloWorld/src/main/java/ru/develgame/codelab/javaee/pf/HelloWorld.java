package ru.develgame.codelab.javaee.pf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class HelloWorld implements Serializable {
    public String getMessage() {
        return "Hello";
    }
}
