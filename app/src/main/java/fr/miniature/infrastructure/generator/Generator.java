package fr.miniature.infrastructure.generator;

import fr.miniature.domain.models.generator.GeneratorInterface;

public class Generator implements GeneratorInterface {
    private static GeneratorInterface instance;
    private long MAX = 1000000;
    private int MIN = 1000;
    
    public static GeneratorInterface getInstance(){
        if(instance == null){
            instance = new Generator();
        }

        return instance;
    }
    
    public String generateRandomID(){
        int id = (int) (Math.random() * (MAX - MIN + 1) + MIN);
        return Integer.toString(id);
    }
}
