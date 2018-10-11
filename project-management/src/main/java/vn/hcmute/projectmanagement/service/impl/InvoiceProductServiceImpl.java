package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Invoice;
import vn.hcmute.projectmanagement.entity.Invoice_Product;
import vn.hcmute.projectmanagement.repository.InvoiceProductRepository;
import vn.hcmute.projectmanagement.service.InvoiceProductService;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {
    @Autowired
    private InvoiceProductRepository invoiceProductRepository;


    @Override
    public Invoice_Product addInvoiceProduct(Invoice_Product invoice_product) {
        return invoiceProductRepository.save(invoice_product);
    }
}
