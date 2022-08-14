package rmm.ninjaone.payments.application.payers.UpdatePayer;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.payments.domain.contracts.payers.PayerSrv;

@Component
public class UpdatePayerHandler extends BaseHandler<UpdatePayerCommand, UpdatePayerResult> {
    private PayerSrv payerSrv;

    public UpdatePayerHandler(UserContext context, PayerSrv payerSrv) {
        super(context);
        this.payerSrv = payerSrv;
    }

    @Override
    public UpdatePayerResult handle(UpdatePayerCommand command) {
        var payer = payerSrv.update(command.getId(), command.getName());

        var result = new UpdatePayerResult();
        result.setId(payer.getId());
        result.setName(payer.getName());

        return result;
    }
}
