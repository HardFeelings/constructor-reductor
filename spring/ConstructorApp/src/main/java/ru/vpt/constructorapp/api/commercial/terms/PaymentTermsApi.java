package ru.vpt.constructorapp.api.commercial.terms;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.commercial.terms.dto.PaymentTermsDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface PaymentTermsApi {

    @GetMapping("/security/paymentTerms")
    ResponseEntity<ResponseDto<List<PaymentTermsDto>>> getAll();

    @GetMapping("/security/paymentTerms/{id}")
    ResponseEntity<ResponseDto<PaymentTermsDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/admin/paymentTerms")
    ResponseEntity<ResponseDto<PaymentTermsDto>> save(@RequestBody PaymentTermsDto paymentTermsDto);

    @DeleteMapping("/security/admin/paymentTerms/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
