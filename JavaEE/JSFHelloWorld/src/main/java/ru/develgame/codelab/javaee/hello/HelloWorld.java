package ru.develgame.codelab.javaee.hello;

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
