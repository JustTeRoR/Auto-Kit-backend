package com.justterror.auto_kit.modelYear.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="ModelYear")
public class ModelYear implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "Year")
    @NotNull
    private int year;
/*
    @Column(name = "CarDetailsJSON")
    @NotNull

    //TODO: Define which is appropriate dataType to store JSON and finish the entity class.
    private JsonObject carDetailsJSON;*/
}
