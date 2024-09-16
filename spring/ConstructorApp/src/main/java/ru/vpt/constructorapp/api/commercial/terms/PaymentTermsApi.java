package ru.vpt.constructorapp.api.commercial.terms;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/admin/paymentTerms")
    ResponseEntity<ResponseDto<PaymentTermsDto>> save(@RequestBody PaymentTermsDto paymentTermsDto);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/security/admin/paymentTerms/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
