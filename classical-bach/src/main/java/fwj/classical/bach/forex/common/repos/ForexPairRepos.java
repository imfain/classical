package fwj.classical.bach.forex.common.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fwj.classical.bach.forex.common.entity.ForexPair;

@RepositoryRestResource(exported = false)
public interface ForexPairRepos extends JpaRepository<ForexPair, Integer> {

	
}
