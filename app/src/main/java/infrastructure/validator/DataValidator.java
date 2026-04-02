package infrastructure.validator;

import infrastructure.errors.errorWithRedirection.ErrorWithRedirection;
import infrastructure.errors.invalideData.InvalideData;
import jakarta.servlet.http.HttpServletRequest;

public final class DataValidator {
    private static DataValidator instance;
    private String regexForScriptInjection = "<";

    public static DataValidator getInstance(){
        if(instance == null){
            instance = new DataValidator();
        }
        return instance;
    }
    

    public void check(HttpServletRequest req, String ...values){
        String method = req.getMethod();
        for(String key: values){
            String value = req.getParameter(key);
            if(value == null || value.isBlank() || value.isEmpty()){
                String message = key+" invalide";
                if(method.toLowerCase().equals("get")){
                    throw new ErrorWithRedirection("/index.html");
                }else{
                    throw new InvalideData(message);
                }
                
            }

        }
        
    }

    public String escapedForbiddenCharater(String value){
        return value.replaceAll(regexForScriptInjection, "");
        
    }
}
