package peaksoft.dto.responseView;

import lombok.Getter;
import lombok.Setter;
import peaksoft.responses.CompanyResponse;

import java.util.List;

@Getter
@Setter
public class CompanyResponseView {
    private List<CompanyResponse> companyResponses;
    private int currentPage;
    private int totalPage;
}
