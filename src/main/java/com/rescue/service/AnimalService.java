package com.rescue.service;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Animal;
import com.rescue.entity.Statics;

import java.util.List;


public interface AnimalService {
    void deleteAnimal(Integer id);

    void addAnimal(Animal animal);

    void updateAnimal(Animal animal);

   Animal getAnimalById(Integer id);

    PageInfo<Animal> findAnimal(Animal animal, Integer pageNum, Integer pageSize);

    List<Animal> selectAll(String state);

    void updateState(Animal animal);

    List<Statics> selectAnimalStatics();

    List<Statics> selectAnimalStaticsByCate();
}
