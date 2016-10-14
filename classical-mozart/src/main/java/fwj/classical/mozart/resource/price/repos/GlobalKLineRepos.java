package fwj.classical.mozart.resource.price.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fwj.classical.mozart.resource.price.entity.GlobalKLine;

@RepositoryRestResource(exported = false)
public interface GlobalKLineRepos extends JpaRepository<GlobalKLine, Integer> {

	GlobalKLine findTopByCodeOrderByDtDesc(String code);
	
}
