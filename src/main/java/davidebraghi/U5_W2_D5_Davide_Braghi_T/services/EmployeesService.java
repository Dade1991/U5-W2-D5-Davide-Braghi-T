package davidebraghi.U5_W2_D5_Davide_Braghi_T.services;

import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.Employee;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.exceptions.BadRequestException;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.exceptions.NotFoundException;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.payloads.NewEmployeeDTO;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;

public class EmployeesService {
    @Autowired
    private EmployeeRepo employeeRepo;

    // Paginazione // findAll

    public Page<Employee> getEmployees(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return employeeRepo.findAll(pageable);
    }

    // findById

    public Employee findById(long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // Save

    public Employee save(NewEmployeeDTO body) throws IOException {
        if (employeeRepo.findByUsername(body.username()).isPresent()) {
            throw new BadRequestException("The username " + body.username() + " is already in use. Try another username.");
        }

        Employee newEmployee = new Employee();
        newEmployee.setUsername(body.username());
        newEmployee.setSurname(body.surname());
        newEmployee.setName(body.name());
        newEmployee.setEmail(body.email());

        return employeeRepo.save(newEmployee);
    }

    // findByIdAndUpdate

    public Employee findByIdAndUpdate(long id, Employee body) {
        Employee found = this.findById(id);
        found.setUsername(body.getUsername());
        found.setSurname(body.getSurname());
        found.setName(body.getName());
        found.setEmail(body.getEmail());

        return employeeRepo.save(found);
    }

    // findByIdAndDelete

    public void findByIdAndDelete(long id) {
        Employee found = this.findById(id);
        employeeRepo.delete(found);
    }
}