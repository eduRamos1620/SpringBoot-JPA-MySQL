package com.ramos.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ramos.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.ramos.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

    @Query("select new com.ramos.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select p.name from Person p where id=?1")
    String getNameById(Long id);
    
    @Query("select concat(p.name, ' ', p.lastname) from Person p where id=?1")
    String getFullNameById(Long id);
    
    @Query("select p.id from Person p where id=?1")
    Long getIdById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);
    
    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);
    
    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();
    
    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String prgrammingLanguage, String name);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();
}
