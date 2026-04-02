package fr.miniature.infrastructure.redirection;

import fr.miniature.infrastructure.errors.errorWithRedirection.ErrorWithRedirection;

public class Redirection extends ErrorWithRedirection {

    public Redirection(String path){
        super(path);
    }
}
