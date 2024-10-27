package com.rescue.dao;

import com.rescue.entity.Animal;
import com.rescue.entity.Statics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface AnimalDao {
    void deleteAnimal(@Param("id") Integer id);

    void addAnimal(@Param("animal") Animal animal);

    void updateAnimal(@Param("animal") Animal animal);

   Animal getAnimalById(@Param("id") Integer id);

    List<Animal> findAnimal(@Param("animal") Animal animal);

    List<Animal> selectAll(@Param("state") String state);

    void updateState(@Param("animal") Animal animal);

    List<Statics> selectAnimalStatics();

    List<Statics> selectAnimalStaticsByCate();
}
