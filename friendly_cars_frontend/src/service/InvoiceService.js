import axios from "axios";

const INVOICE_BASE_URL = "http://localhost:8080/api/v1/invoices";

class InvoiceService {
    createInvoice(invoice) {
        return axios.post(INVOICE_BASE_URL, invoice)
            .then(response => response.data)
    }

    getAllInvoices() {
        return axios.get(INVOICE_BASE_URL)
            .then(response => response.data)
    }

    getInvoiceById(invoiceId) {
        return axios.get(INVOICE_BASE_URL + `/${invoiceId}`)
            .then(response => response.data)
    }

    updateInvoice(invoiceId, newInvoice) {
        return axios.put(INVOICE_BASE_URL + `/${invoiceId}`, newInvoice)
            .then(response => response.data)
    }

    partialUpdateInvoice(invoiceId, newInvoice) {
        return axios.patch(INVOICE_BASE_URL + `/${invoiceId}`, newInvoice)
            .then(response => response.data)
    }

    deleteInvoice(invoiceId) {
        return axios.delete(INVOICE_BASE_URL + `/${invoiceId}`, invoiceId)
            .then(response => response.data)
    }
}

export default new InvoiceService()
