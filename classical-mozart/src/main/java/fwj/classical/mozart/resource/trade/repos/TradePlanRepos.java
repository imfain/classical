package fwj.classical.mozart.resource.trade.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fwj.classical.mozart.resource.trade.entity.TradeGroup;

@RepositoryRestResource(exported = false)
public interface TradePlanRepos extends JpaRepository<TradeGroup, Integer> {

}
