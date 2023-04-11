package whattoeattoday.whatoeattoday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whattoeattoday.whatoeattoday.domain.UserEntity;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    Optional <UserEntity> findByEmail(String email);
}
