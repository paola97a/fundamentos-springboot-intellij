package com.fundamentosspringboot.fundamentos.Repository;

import com.fundamentosspringboot.fundamentos.Dto.UserDto;
import com.fundamentosspringboot.fundamentos.Entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/*Para el ejercicio pageable se reemplaza JpaRepository por PagingAndSortingRepository*/
@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    @Query("select u from User u Where u.email=?1") //donde u sea igual a un parametro
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u Where u.name like ?1%")  //donde u sea igual a un parametro % ->por like
    List<User> findAndSort(String name, Sort sort) ;

    //@Query
    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    //List<User> findByNameLikeOrderByEmailDesc(String name);

    /*Misma función del Liqe sin usar la palabra*/
    List<User> findByNameContainingOrderByEmailDesc(String name);

    @Query("Select new com.fundamentosspringboot.fundamentos.Dto.UserDto(u.id,u.name,u.birthDate)" +
            "from User u where u.birthDate=:parametroFecha and u.email=:parametroEmail")
    Optional<UserDto> getAllByBirthDateAnAndEmail(@Param("parametroFecha") LocalDate date,
                                                  @Param("parametroEmail") String email);

    /*Cuando se compila con PagingAndSortingRepository se generá un error para solucionarlo hay que agregar
    * lo siguiente*/
    List<User> findAll();
}
