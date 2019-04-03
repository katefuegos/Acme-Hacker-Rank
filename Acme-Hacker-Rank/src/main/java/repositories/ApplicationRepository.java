package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends
		JpaRepository<Application, Integer> {

	@Query("select a from Application a where a.position.company.id=?1")
	Collection<Application> findByCompanyId(int companyId);

	@Query("select a from Application a where a.hacker.id=?1")
	Collection<Application> findByHackerId(int hackerId);

}
