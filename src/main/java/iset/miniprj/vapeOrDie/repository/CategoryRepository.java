package iset.miniprj.vapeOrDie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iset.miniprj.vapeOrDie.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
