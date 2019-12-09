package br.com.microservice.organizationservice.controller;

import br.com.microservice.organizationservice.client.DepartmentClient;
import br.com.microservice.organizationservice.client.EmployeeClient;
import br.com.microservice.organizationservice.model.Organization;
import br.com.microservice.organizationservice.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizationController {
    public static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    EmployeeClient employeeClient;

    @Autowired
    DepartmentClient departmentClient;

    @PostMapping("/")
    public Organization add(@RequestBody Organization organization) {
        LOGGER.info("Organization add: {}", organization);
        return organizationRepository.add(organization);
    }

    @GetMapping("/")
    public List<Organization> findAll() {
        LOGGER.info("Organization find");
        return organizationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Organization findById(@PathVariable("id") Long id) {
        LOGGER.info("Organization find: id = {}", id);
        return organizationRepository.findById(id);
    }

    @GetMapping("/{id}/with-departments")
    public Organization findByIdWithDepartments(@PathVariable("id") Long id) {
        LOGGER.info("Organization find: id = {}", id);
        Organization organization = organizationRepository.findById(id);
        organization.setDepartments(departmentClient.findByOrganizationId(organization.getId()));
        return organization;
    }

    @GetMapping("/{id}/with-departments-and-employees")
    public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
        LOGGER.info("Organization find: id = {}", id);
        Organization organization = organizationRepository.findById(id);
        organization.setDepartments(departmentClient.findByOrganizationWithEmployees(organization.getId()));
        return organization;
    }

    @GetMapping("/{id}/with-employees")
    public Organization findByIdWithEmployees(@PathVariable("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        Organization organization = organizationRepository.findById(id);
        organization.setEmployees(employeeClient.findByOrganizationId(organization.getId()));
        return organization;
    }
}
