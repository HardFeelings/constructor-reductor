package ru.vpt.constructorapp.service.commercial;
import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.SaveOptions;
import com.aspose.cells.Workbook;
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
        Workbook workbook = null;
        try {
            workbook = new Workbook(xls);
            workbook.save(baos, SaveFormat.PDF);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }
}

