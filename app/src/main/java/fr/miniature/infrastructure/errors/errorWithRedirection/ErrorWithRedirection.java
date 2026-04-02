package fr.miniature.infrastructure.errors.errorWithRedirection;

public class ErrorWithRedirection extends Error {
    private String pathToRedirect;

    public ErrorWithRedirection(String path){
        this.pathToRedirect = path;
    }

    public String getPath(){
        return pathToRedirect;
    }
}
