package br.com.microservice.organizationservice.repository;

import br.com.microservice.organizationservice.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrganizationRepository {

    private List<Organization> organizations = new ArrayList<>();

    public Organization add(Organization organization) {
        organization.setId((long) (organizations.size()+1));
        organizations.add(organization);
        return organization;
    }

    public List<Organization> findAll() {
        return organizations;
    }

    public Organization findById(Long id) {
        Optional<Organization> organization = organizations.stream()
                .filter(o -> o.getId().equals(id)).findFirst();
        return organization.orElse(null);
    }
}
