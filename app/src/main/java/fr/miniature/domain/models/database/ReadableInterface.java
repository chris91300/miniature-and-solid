package fr.miniature.domain.models.database;

import java.util.ArrayList;

import fr.miniature.domain.models.entities.EntityEnum;
import fr.miniature.domain.models.entities.EntityInterface;

public interface ReadableInterface {
    public EntityInterface getEntityWithId(String ID, EntityEnum entityName);
    public ArrayList<Object> getEntities(EntityEnum entityName);
}
