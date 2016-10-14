package fwj.classical.mozart.resource.price.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fwj.classical.mozart.resource.price.entity.ProdSpotPrice;

@RepositoryRestResource(exported = false)
public interface ProdSpotPriceRepos extends JpaRepository<ProdSpotPrice, Integer> {

	List<ProdSpotPrice> findByCodeOrderByDtAsc(String code);

	List<ProdSpotPrice> findByCodeAndDtBetweenOrderByDtAsc(String code, Date startDt, Date endDt);

	int deleteByCodeAndDtBetween(String code, Date startDt, Date endDt);

}
