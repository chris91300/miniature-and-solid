package fr.miniature.infrastructure.errors.invalideData;

public class InvalideData extends Error {
    private String message;

    public InvalideData(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
