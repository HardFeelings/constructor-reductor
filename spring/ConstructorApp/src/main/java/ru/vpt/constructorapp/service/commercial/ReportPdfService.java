package ru.vpt.constructorapp.service.commercial;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;

import java.io.*;

@Service
@RequiredArgsConstructor
public class ReportPdfService {
    private final ReportService reportService;

    public InputStream report(CommercialPropEntity byId) {
        InputStream xls = reportService.report(byId);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        return new ByteArrayInputStream(baos.toByteArray());
    }
}

