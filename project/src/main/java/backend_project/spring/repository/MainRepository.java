package backend_project.spring.repository;

import backend_project.spring.domain.MainDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<MainDomain, String> {


}
