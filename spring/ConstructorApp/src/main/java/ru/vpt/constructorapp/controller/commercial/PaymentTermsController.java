package ru.vpt.constructorapp.controller.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.commercial.terms.PaymentTermsApi;
import ru.vpt.constructorapp.api.commercial.terms.dto.PaymentTermsDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.commercial.PaymentTermsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentTermsController extends AbstractController implements PaymentTermsApi {

    private final PaymentTermsService paymentTermsService;

    @Override
    public ResponseEntity<ResponseDto<List<PaymentTermsDto>>> getAll() {
        return response(paymentTermsService.getAll());
    }

    @Override
    public ResponseEntity<ResponseDto<PaymentTermsDto>> getById(Long id) {
        return response(paymentTermsService.getById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<PaymentTermsDto>> save(PaymentTermsDto paymentTermsDto) {
        return response(paymentTermsService.save(paymentTermsDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(paymentTermsService.delete(id));
    }
}
