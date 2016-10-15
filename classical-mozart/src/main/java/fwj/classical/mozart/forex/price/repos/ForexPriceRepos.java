package fwj.classical.mozart.forex.price.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fwj.classical.mozart.forex.price.entity.ForexPrice;

@RepositoryRestResource(exported = false)
public interface ForexPriceRepos extends JpaRepository<ForexPrice, Integer> {

	List<ForexPrice> findByCodeAndDtBetweenOrderByDtAsc(String code, Date startDt, Date endDt);
	
	ForexPrice findTopByCodeOrderByDtDesc(String code);

}
