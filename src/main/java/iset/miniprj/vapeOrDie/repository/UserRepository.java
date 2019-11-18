package iset.miniprj.vapeOrDie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iset.miniprj.vapeOrDie.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
