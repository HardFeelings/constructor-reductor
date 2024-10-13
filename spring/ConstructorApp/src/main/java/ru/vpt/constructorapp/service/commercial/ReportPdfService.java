package ru.vpt.constructorapp.service.commercial;

import com.aspose.cloud.cells.api.CellsApi;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;

import java.io.*;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class ReportPdfService {

    private final ReportService reportService;

    public InputStream report(CommercialPropEntity byId)  {
        try
        {
            InputStream xls = reportService.report(byId);
            String clientId = "304c1112-e3b3-453c-bd7b-59f74abb6c88";
            String clientSecret = "4b83766e02e54eb4c976b7b315f42dff";
            CellsApi api = new CellsApi(clientId,clientSecret);

            String inputName = "targetFile-" + byId.getNumber() + ".xlsx";
            String outputName = "report-" + byId.getNumber() + ".pdf";
            String password = null;
            Boolean isAutoFit = false;
            Boolean onlySaveTable = false;
            String format = "PDF";
            File targetFile = new File(inputName);
            FileUtils.copyInputStreamToFile(xls, targetFile);
            api.uploadFile(inputName, targetFile, "constructor");
            api.cellsWorkbookGetWorkbook(inputName,  password,format,
                    isAutoFit, onlySaveTable, null,"constructor",  outputName,"constructor", null);
            File response = api.downloadFile(outputName,"constructor", null );
            System.out.println("XlSX sucessfully converted to PDF format !");
            return new FileInputStream(response);
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
        return null;
    }
}

