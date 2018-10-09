package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Invoice;
import vn.hcmute.projectmanagement.repository.InvoiceRepository;
import vn.hcmute.projectmanagement.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}
