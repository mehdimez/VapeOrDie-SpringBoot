package iset.miniprj.vapeOrDie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iset.miniprj.vapeOrDie.entities.Category;
import iset.miniprj.vapeOrDie.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
