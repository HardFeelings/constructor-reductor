package ru.vpt.constructorapp.service.commercial;

import com.spire.xls.FileFormat;
import com.spire.xls.Workbook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;

import java.io.*;


@Service
@RequiredArgsConstructor
public class ReportPdfService {

    private final ReportService reportService;

    public InputStream report(CommercialPropEntity byId)  {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream xls = reportService.report(byId);
        Workbook workbook = new Workbook();
        workbook.loadFromStream(xls);
        workbook.getConverterSetting().setSheetFitToPage(true);
        workbook.saveToStream(baos, FileFormat.PDF);
        return new ByteArrayInputStream(baos.toByteArray());
    }
}

