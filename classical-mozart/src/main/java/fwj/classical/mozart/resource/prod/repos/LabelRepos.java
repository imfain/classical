package fwj.classical.mozart.resource.prod.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fwj.classical.mozart.resource.prod.entity.Label;

@RepositoryRestResource(exported = false)
public interface LabelRepos extends JpaRepository<Label, Integer> {

	Label findByName(String name);
}
