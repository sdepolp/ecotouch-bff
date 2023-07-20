package cl.ecotouch.bff.controller;

import cl.ecotouch.bff.service.TransBankService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/transbank")
public class TransbankController {

        private final TransBankService transBankService;

        @PostMapping("/create-transaction/{buyOrder}/{amount}")
        public ResponseEntity<Object> createTransaction(@PathVariable("buyOrder") String buyOrder, String sessionId,
                                                        @PathVariable("amount") Double amount){
            try{
                return ResponseEntity.ok(transBankService.createTransaction(buyOrder, sessionId, amount));
            }catch (HttpStatusCodeException ex) {
                return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
            }catch (Exception exception){
                return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
            }
        }

        @PostMapping("/commit-transaction/{token}")
        public ResponseEntity<Object> commitTransaction(@PathVariable("token") String token){
            try{
                return ResponseEntity.ok(transBankService.commitTransaction(token));
            }catch (HttpStatusCodeException ex) {
                return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
            }catch (Exception exception){
                return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
            }
        }

        @GetMapping("/status-transaction/{token}")
        public ResponseEntity<Object> getStatusTransaction(@PathVariable("token") String token){
            try{
                return ResponseEntity.ok(transBankService.getStatusTransaction(token));
            }catch (HttpStatusCodeException ex) {
                return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
            }catch (Exception exception){
                return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
            }
        }

        @PostMapping("/refund-transaction/{token}/{amount}")
        public ResponseEntity<Object> refundTransaction(@PathVariable("token") String token, @PathVariable("amount") Double amount){
            try{
                return ResponseEntity.ok(transBankService.refundTransaction(token,amount));
            }catch (HttpStatusCodeException ex) {
                return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
            }catch (Exception exception){
                return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
            }
        }

        @GetMapping("/capture-transaction/{token}/{buyOrder}/{amount}")
        public ResponseEntity<Object> captureTransaction(@PathVariable("buyOrder") String buyOrder, @PathVariable("token") String token,
                                                         @PathVariable("amount") Double amount, String authorizationCode){
            try{
                return ResponseEntity.ok(transBankService.captureTransaction(token,buyOrder,authorizationCode, amount));
            }catch (HttpStatusCodeException ex) {
                return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(ex.getResponseBodyAsString());
            }catch (Exception exception){
                return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(exception.getMessage());
            }
        }
}
