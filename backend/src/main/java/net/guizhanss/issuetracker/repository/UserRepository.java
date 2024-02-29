package net.guizhanss.issuetracker.repository;

import net.guizhanss.issuetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
