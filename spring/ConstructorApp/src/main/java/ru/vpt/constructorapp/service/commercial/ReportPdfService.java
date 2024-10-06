package ru.vpt.constructorapp.service.commercial;

import com.convertapi.client.Config;
import com.convertapi.client.ConvertApi;
import com.convertapi.client.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;

import java.io.*;
import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
public class ReportPdfService {

    private final ReportService reportService;

    public InputStream report(CommercialPropEntity byId)  {
        InputStream xls = reportService.report(byId);
        Config.setDefaultSecret("secret_hjxdQyEHipEUFevZ");
        InputStream output = null;
        try {
            output = ConvertApi.convert("xlsx", "pdf",
                    new Param("File", xls, "reportTemplate.xlsx")
            ).get().asStream().get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }
}

