package rmm.ninjaone.payments.application.payers.CreatePayer;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.payments.domain.contracts.payers.PayerSrv;

@Component
public class CreatePayerHandler extends BaseHandler<CreatePayerCommand, CreatePayerResult> {
    private PayerSrv payerSrv;

    public CreatePayerHandler(UserContext context, PayerSrv payerSrv) {
        super(context);
        this.payerSrv = payerSrv;
    }

    @Override
    public CreatePayerResult handle(CreatePayerCommand command) {
        var payer = payerSrv.create(command.getId(), command.getName());

        var result = new CreatePayerResult();
        result.setId(payer.getId());
        result.setName(payer.getName());

        return result;
    }
}
