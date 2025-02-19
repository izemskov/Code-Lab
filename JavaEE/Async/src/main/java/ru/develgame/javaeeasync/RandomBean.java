package ru.develgame.javaeeasync;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Random;

@SessionScoped
@Named("randomBean")
public class RandomBean implements Serializable {
    private Random random = new Random();

    public int getRandomValue() {
        return random.nextInt(100);
    }
}
