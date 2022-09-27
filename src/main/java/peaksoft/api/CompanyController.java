package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.CompanyRequest;
import peaksoft.dto.responseView.CompanyResponseView;
import peaksoft.responses.CompanyResponse;
import peaksoft.responses.SimpleResponse;
import peaksoft.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Company API", description = "User with role admin can add, update, delete or get all companies")
public class CompanyController {
    private final CompanyService service;

    @PostMapping("/save")
    public CompanyResponse create(@RequestBody CompanyRequest company) {
        return service.addCompany(company);
    }

    @GetMapping("/get/{id}")
    public CompanyResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete/{companyId}")
    public SimpleResponse deleteById(@PathVariable Long companyId) {
        return service.deleteById(companyId);
    }

    @PutMapping("/block/{id}")
    public CompanyResponse blockStudent(@PathVariable Long id) {
        return service.block(id);

    }

    @PutMapping("/update/{id}")
    public CompanyResponse update(@PathVariable Long id, @RequestBody CompanyRequest request) {
        return service.updateCompany(id, request);
    }

    @GetMapping("/getAll")
    public List<CompanyResponse> getAll() {
        return service.findAllCompany();
    }

    public CompanyResponseView getAllCompaniesPagination(@RequestParam(name = "text", required = false) String text,
                                                         @RequestParam int page,
                                                         @RequestParam int size) {
        return service.getAllCompaniesPagination(text, page, size);
    }
}