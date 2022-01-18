package com.fundamentosspringboot.fundamentos.Repository;

import com.fundamentosspringboot.fundamentos.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Esta interface representa la entidad Post*/
@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

}
