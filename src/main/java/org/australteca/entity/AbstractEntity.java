package org.australteca.entity;

import javax.persistence.*;

/**
 * Created by tomi on 15/04/17.
 */

/*the inheritance is visible in the domain model only and each database table
contains both the base class and the subclass properties.*/

@MappedSuperclass
public abstract class AbstractEntity implements EntityInterface{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    protected Integer id;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

}
