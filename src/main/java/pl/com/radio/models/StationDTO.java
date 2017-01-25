/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.models;

import pl.com.radio.entity.StationEntity;

/**
 *
 * @author bartek
 */
public class StationDTO implements Mapper<StationEntity, StationDTO> {

    private Long id;
    private String name;
    private String path;

    @Override
    public StationDTO populate(StationEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.path = entity.getPath();
        return this;
    }

    @Override
    public void reversePopulate(StationEntity entity) {
        entity.setId(id);
        entity.setName(name);
        entity.setPath(path);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
