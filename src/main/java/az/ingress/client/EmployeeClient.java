package az.ingress.client;

import az.ingress.model.client.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-employee", url = "http://localhost:8080")
public interface EmployeeClient {
    @GetMapping("/internal/v1/employees/{id}")
    EmployeeDto getEmployee(@PathVariable Long id);
}
