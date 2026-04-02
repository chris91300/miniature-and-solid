package infrastructure.redirection;

import infrastructure.errors.errorWithRedirection.ErrorWithRedirection;

public class Redirection extends ErrorWithRedirection {

    public Redirection(String path){
        super(path);
    }
}
