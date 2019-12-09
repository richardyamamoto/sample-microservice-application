package br.com.microservice.employeeservice.repository;

import br.com.microservice.employeeservice.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public Employee add(Employee employee) {
        employee.setId((long) (employees.size() + 1));
        employees.add(employee);
        return employee;
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(Long id) {
        Optional<Employee> employee = employees.stream()
                .filter(e -> e.getId().equals(id)).findFirst();
        return employee.orElse(null);
    }

    public List<Employee> findByOrganizationId(Long organizationId) {
        return employees
                .stream()
                .filter(e -> e.getOrganizationId().equals(organizationId))
                .collect(Collectors.toList());

    }

    public List<Employee> findByDepartmentId(Long departmentId) {
        return employees
                .stream()
                .filter(e -> e.getDepartmentId().equals(departmentId))
                .collect(Collectors.toList());
    }

}
