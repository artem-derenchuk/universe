package com.test;

import com.test.model.Lord;
import com.test.repository.LordRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LordRepositoryTests {
    @Autowired
    private LordRepository lordRepo;

    @Test
    public void testSaveLord() {
        Lord lord = new Lord(16L,"Вельзевул", 700);
        lordRepo.save(lord);
        Optional<Lord> lord1 = lordRepo.findById(16L);
        System.out.println(lord1.get().toString());
        assertNotNull(lord1.get());
        assertEquals(lord.getId(),lord1.get().getId());
        assertEquals(lord.getName(),lord1.get().getName());
        assertEquals(lord.getAge(),lord1.get().getAge());
    }
    @Test
    public void testFindTop10YoungestLords() {
        List<Lord> lords = new ArrayList<>();
        lords.add(new Lord(1L,"Мавродий",66));
        lords.add(new Lord(2L,"Григорий",58));
        lords.add(new Lord(3L,"Пан",32));
        lords.add(new Lord(4L,"Лангуст",51));
        lords.add(new Lord(5L,"Кирилл",77));
        lords.add(new Lord(6L,"Адамс",36));
        lords.add(new Lord(7L,"Ирис",98));
        lords.add(new Lord(8L,"Лавер",78));
        lords.add(new Lord(9L,"Горох",87));
        lords.add(new Lord(10L,"Баклажан",85));
        lords.add(new Lord(11L,"Вильгельм",59));
        lords.add(new Lord(12L,"Вильгельм",90));
        List <Lord> lords1 = lordRepo.findTop10ByOrderByAgeAsc();
        assertNotEquals(lords,lords1);
    }
    @Test
    public void testFindSlackerLords(){
        List<Lord> lords = new ArrayList<>();
        lords.add(new Lord(3L,"Август",600));
        lords.add(new Lord(4L,"Авдей",200));
        lords.add(new Lord(5L,"Автандил",400));
        lords.add(new Lord(6L,"Адам",900));
        lords.add(new Lord(7L,"Адис",700));
        lords.add(new Lord(8L,"Адриан",500));
        lords.add(new Lord(9L,"Азарий",5419));
        lords.add(new Lord(10L,"Аким",5000));
        lords.add(new Lord(11L,"Альберт",1000));
        lords.add(new Lord(12L,"Альфред",800));
        List<Lord> lords1 = lordRepo.findSlackerLords();
        assertNotEquals(lords, lords1);
    }
}
