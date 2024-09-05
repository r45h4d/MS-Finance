package az.ingress.controller;

import az.ingress.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
public class Controller {
    private final FinanceService financeService;

    @GetMapping("/employees/{employeeId}/calculateSalary")
    @ResponseStatus(OK)
    public void calculateBonusByEmployee(@PathVariable Long employeeId){
        financeService.calculateBonusByEmployee(employeeId);
    }
}
