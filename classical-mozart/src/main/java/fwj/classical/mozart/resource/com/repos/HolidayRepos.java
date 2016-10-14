package fwj.classical.mozart.resource.com.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fwj.classical.mozart.resource.com.entity.Holiday;

@RepositoryRestResource(exported = false)
public interface HolidayRepos extends JpaRepository<Holiday, Integer> {

}
