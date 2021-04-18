package com.justterror.auto_kit.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table(name="Model")
public class Model  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "Name")
    @NotNull
    private String name;

    @Column(name = "TrimName")
    @NotNull
    private String trimName;

    @Column(name = "MakeID")
    @NotNull
    private long makeID;

    @Column(name = "VpicID")
    @NotNull
    private long vpicID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrimName() {
        return trimName;
    }

    public void setTrimName(String trimName) {
        this.trimName = trimName;
    }

    public long getMakeID() {
        return makeID;
    }

    public void setMakeID(long makeID) {
        this.makeID = makeID;
    }

    public long getVpicID() {
        return vpicID;
    }

    public void setVpicID(long vpicID) {
        this.vpicID = vpicID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (ID != model.ID) return false;
        if (makeID != model.makeID) return false;
        if (vpicID != model.vpicID) return false;
        if (name != null ? !name.equals(model.name) : model.name != null) return false;
        return trimName != null ? trimName.equals(model.trimName) : model.trimName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (trimName != null ? trimName.hashCode() : 0);
        result = 31 * result + (int) (makeID ^ (makeID >>> 32));
        result = 31 * result + (int) (vpicID ^ (vpicID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Model{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", trimName='" + trimName + '\'' +
                ", makeID=" + makeID +
                ", vpicID=" + vpicID +
                '}';
    }
}
