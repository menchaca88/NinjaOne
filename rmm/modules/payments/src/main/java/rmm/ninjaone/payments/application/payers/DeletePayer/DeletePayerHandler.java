package rmm.ninjaone.payments.application.payers.DeletePayer;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.payments.domain.contracts.payers.PayerSrv;

@Component
public class DeletePayerHandler extends BaseHandler<DeletePayerCommand, DeletePayerResult> {
    private PayerSrv payerSrv;

    public DeletePayerHandler(UserContext context, PayerSrv payerSrv) {
        super(context);
        this.payerSrv = payerSrv;
    }

    @Override
    public DeletePayerResult handle(DeletePayerCommand command) {
        var payer = payerSrv.delete(command.getId());

        var result = new DeletePayerResult();
        result.setId(payer.getId());
        result.setName(payer.getName());

        return result;
    }
}
