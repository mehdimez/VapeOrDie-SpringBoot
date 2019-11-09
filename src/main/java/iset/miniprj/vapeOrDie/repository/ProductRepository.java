package iset.miniprj.vapeOrDie.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import iset.miniprj.vapeOrDie.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "SELECT * FROM `product` WHERE id_category = ?1", nativeQuery = true)
	List<Product> findByEmailAddress(Long idCategory);
}
