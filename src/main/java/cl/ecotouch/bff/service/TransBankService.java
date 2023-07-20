package cl.ecotouch.bff.service;

import cl.transbank.webpay.exception.*;
import cl.transbank.webpay.webpayplus.responses.*;

import java.io.IOException;

public interface TransBankService {
    WebpayPlusTransactionCreateResponse createTransaction(String buyOrder, String sessionId, Double amount) throws TransactionCreateException, IOException;
    WebpayPlusTransactionCommitResponse commitTransaction(String token) throws TransactionCommitException, IOException;
    WebpayPlusTransactionStatusResponse getStatusTransaction(String token) throws IOException, TransactionStatusException;
    WebpayPlusTransactionRefundResponse refundTransaction(String token, Double amount) throws TransactionRefundException, IOException;
    WebpayPlusTransactionCaptureResponse captureTransaction(String token, String buyOrder, String authorizationCode, Double amount) throws TransactionCaptureException, IOException;
}