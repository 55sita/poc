package com.handicraftsnepal.shecrafts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="manufacturer")
public class HandicraftBy implements Serializable {
    @Id
    @GeneratedValue
    private int manufacturerId;
    private String manufacturerName;
    private int itemSold;
    private int experience;

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public int getItemSold() {
        return itemSold;
    }

    public void setItemSold(int itemSold) {
        this.itemSold = itemSold;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "HandicraftBy{" +
                "manufacturerId='" + manufacturerId + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", itemSold=" + itemSold +
                ", experience=" + experience +
                '}';
    }
}
