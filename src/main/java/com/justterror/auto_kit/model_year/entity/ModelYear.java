package com.justterror.auto_kit.model_year.entity;

import javax.json.Json;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "model_year", schema = "public")
public class ModelYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="year")
    @NotNull
    private int year;

    @Column(name="car_details_json")
    @NotNull
    private String carDetailsJson;

    @Column(name="schedule_json")
    @NotNull
    private String scheduleJson;

    @Column(name="model_id")
    @NotNull
    private long modelId;

    @Column(name="epic_id")
    @NotNull
    private long epicId;

    @Column(name="user_id")
    @NotNull
    private long userId;

    public ModelYear() {}

    public ModelYear(int year, String carDetailsJson, String scheduleJson, long modelId, int epicId, long userId) {
        this.year = year;
        this.carDetailsJson = carDetailsJson;
        this.scheduleJson = scheduleJson;
        this.modelId = modelId;
        this.epicId = epicId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCarDetailsJson() {
        return carDetailsJson;
    }

    public void setCarDetailsJson(String carDetailsJson) {
        this.carDetailsJson = carDetailsJson;
    }

    public String getScheduleJson() {
        return scheduleJson;
    }

    public void setScheduleJson(String scheduleJson) {
        this.scheduleJson = scheduleJson;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public long getEpicId() {
        return epicId;
    }

    public void setEpicId(long epicId) {
        this.epicId = epicId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelYear modelYear = (ModelYear) o;

        if (id != modelYear.id) return false;
        if (year != modelYear.year) return false;
        if (modelId != modelYear.modelId) return false;
        if (epicId != modelYear.epicId) return false;
        if (userId != modelYear.userId) return false;
        if (carDetailsJson != null ? !carDetailsJson.equals(modelYear.carDetailsJson) : modelYear.carDetailsJson != null)
            return false;
        return scheduleJson != null ? scheduleJson.equals(modelYear.scheduleJson) : modelYear.scheduleJson == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + year;
        result = 31 * result + (carDetailsJson != null ? carDetailsJson.hashCode() : 0);
        result = 31 * result + (scheduleJson != null ? scheduleJson.hashCode() : 0);
        result = 31 * result + (int) (modelId ^ (modelId >>> 32));
        result = 31 * result + (int) (epicId ^ (epicId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ModelYear{" +
                "id=" + id +
                ", year=" + year +
                ", carDetailsJson='" + carDetailsJson + '\'' +
                ", scheduleJson='" + scheduleJson + '\'' +
                ", modelId=" + modelId +
                ", epicId=" + epicId +
                ", userId=" + userId +
                '}';
    }
}
