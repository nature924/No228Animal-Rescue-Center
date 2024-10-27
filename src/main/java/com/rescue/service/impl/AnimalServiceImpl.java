package com.rescue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rescue.dao.AnimalDao;
import com.rescue.dao.CategoryDao;
import com.rescue.entity.Animal;
import com.rescue.entity.Category;
import com.rescue.entity.Statics;
import com.rescue.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnimalServiceImpl implements AnimalService {


    @Autowired
    AnimalDao dao;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public void deleteAnimal(Integer id) {
        dao.deleteAnimal(id);
    }


    @Override
    public void addAnimal(Animal animal) {
        dao.addAnimal(animal);
    }

    @Override
    public void updateAnimal(Animal animal) {
        dao.updateAnimal(animal);
    }

    @Override
    public Animal getAnimalById(Integer id) {
        return   dao.getAnimalById(id);

    }

    @Override
    public PageInfo<Animal> findAnimal(Animal animal, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Animal> list = dao.findAnimal(animal);
        for(Animal animal1 :list){
            animal1.setCategory(categoryDao.getCategoryById(animal1.getC_id()));
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Animal> selectAll(String state) {
        return  dao.selectAll(state);
    }

    @Override
    public void updateState(Animal animal) {
        dao.updateState(animal);
    }

    @Override
    public List<Statics> selectAnimalStatics() {
        return dao.selectAnimalStatics();
    }

    @Override
    public List<Statics> selectAnimalStaticsByCate() {
        return dao.selectAnimalStaticsByCate();
    }

}
