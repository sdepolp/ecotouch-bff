package cl.ecotouch.bff.service.impl;

import cl.ecotouch.bff.service.TransBankService;
import cl.transbank.common.IntegrationType;
import cl.transbank.webpay.common.WebpayOptions;
import cl.transbank.webpay.exception.*;
import cl.transbank.webpay.webpayplus.WebpayPlus;
import cl.transbank.webpay.webpayplus.responses.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransBankServiceImpl implements TransBankService {
    @Value("${props.transbank.tbk-api-key-id}")
    String apiKeyId;
    @Value("${props.transbank.tbk-api-key-secret}")
    String apiKeySecret;
    @Value("${props.transbank.integration-type}")
    String integrationType;
    @Value("${props.transbank.returnUrl}")
    String returnUrl;

    @Override
    public WebpayPlusTransactionCreateResponse createTransaction(String buyOrder, String sessionId, Double amount) throws TransactionCreateException, IOException {
        WebpayPlus.Transaction tx = new WebpayPlus.Transaction(new WebpayOptions(apiKeyId, apiKeySecret, getIntegrationType()));
        return tx.create(buyOrder, sessionId, amount, returnUrl);
    }

    @Override
    public WebpayPlusTransactionCommitResponse commitTransaction(String token) throws TransactionCommitException, IOException {
        WebpayPlus.Transaction tx = new WebpayPlus.Transaction(new WebpayOptions(apiKeyId, apiKeySecret, getIntegrationType()));
        return tx.commit(token);
    }

    @Override
    public WebpayPlusTransactionStatusResponse getStatusTransaction(String token) throws IOException, TransactionStatusException {
        WebpayPlus.Transaction tx = new WebpayPlus.Transaction(new WebpayOptions(apiKeyId, apiKeySecret, getIntegrationType()));
        return tx.status(token);
    }

    @Override
    public WebpayPlusTransactionRefundResponse refundTransaction(String token, Double amount) throws TransactionRefundException, IOException {
        WebpayPlus.Transaction tx = new WebpayPlus.Transaction(new WebpayOptions(apiKeyId, apiKeySecret, getIntegrationType()));
        return tx.refund(token, amount);
    }

    @Override
    public WebpayPlusTransactionCaptureResponse captureTransaction(String token, String buyOrder, String authorizationCode, Double amount) throws TransactionCaptureException, IOException {
        WebpayPlus.Transaction tx = new WebpayPlus.Transaction(new WebpayOptions(apiKeyId, apiKeySecret, getIntegrationType()));
        return tx.capture(token, buyOrder, authorizationCode, amount);
    }

    private IntegrationType getIntegrationType(){
        return Enum.valueOf(IntegrationType.class, integrationType);
    }
}
