package org.australteca.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by tomi on 15/04/17.
 */

@MappedSuperclass
public abstract class SuperEntity implements EntityInterface{

    @Id
    @GeneratedValue
    @Column(name = "ENTITY_ID")
    protected int id;

    public int getId(){
        return id;
    }
}
