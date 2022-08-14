package rmm.ninjaone.payments.infrastructure.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rmm.ninjaone.buildingblocks.infrastructure.api.BaseController;
import rmm.ninjaone.payments.application.payments.PayBill.PayBillCommand;

@RestController
@RequestMapping(path = "${api.payments}")
public class PaymentsController extends BaseController {
    @PostMapping("/pay")
    public ResponseEntity<PaymentResponse> pay() {
        var command = new PayBillCommand();

        var result = pipeline.send(command);
        var response = modelMapper.map(result, PaymentResponse.class);

        return ResponseEntity.ok(response);
    }
}