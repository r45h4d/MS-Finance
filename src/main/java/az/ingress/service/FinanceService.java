package az.ingress.service;

import az.ingress.client.EmployeeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinanceService {
    private final EmployeeClient employeeClient;

    public void calculateBonusByEmployee(Long employeeId){
        var employee = employeeClient.getEmployee(employeeId);
        log.info("Employee id: {}, salary: {}, bonus: {}", employee.getId(), employee.getSalary(), employee.getSalary().multiply(BigDecimal.valueOf(1.5)));
    }
}
