package domain.models.publication;

import java.util.Date;
import domain.models.entities.EntityInterface;
import domain.models.generator.GeneratorInterface;
import infrastructure.generator.Generator;

public abstract class Publication implements EntityInterface {
    private String id; 
    private String userID;
    private String content;
    private long createdAt;
    private GeneratorInterface generator = Generator.getInstance();

    public Publication(String userID,String content){
        this.userID = userID;
        this.content = content;
        this.id = generator.generateRandomID();
        this.createdAt = new Date().getTime();
    }


    public String getID() {
        return id;
    }



    public String getUserID() {
        return userID;
    }



    public String getContent() {
        return content;
    }



    public long getCreatedAt() {
        return createdAt;
    }
}
