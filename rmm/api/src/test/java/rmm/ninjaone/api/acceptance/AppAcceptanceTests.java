package rmm.ninjaone.api.acceptance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

import rmm.ninjaone.api.data.AcceptanceData;
import rmm.ninjaone.api.endpoints.authentication.LoginUserRequest;
import rmm.ninjaone.api.endpoints.authentication.RegisterUserRequest;
import rmm.ninjaone.api.endpoints.authentication.UserLoggedResponse;
import rmm.ninjaone.api.endpoints.authentication.UserRegisteredResponse;
import rmm.ninjaone.api.support.setup.RootAccount;
import rmm.ninjaone.invoices.endpoints.InvoiceResponse;
import rmm.ninjaone.payments.infrastructure.endpoints.PaymentResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppAcceptanceTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RootAccount root;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
	void createUser_CreateTypes_CreateItems_PayBill_DownloadInvoice() throws Exception {
        var token = login(AcceptanceData.loginRoot(root));

        var windowsId = createDeviceType(token, AcceptanceData.createWindows());
        var macId = createDeviceType(token, AcceptanceData.createMac());

        var antivirusId = createServiceType(token, AcceptanceData.createAntivirus(windowsId, macId));
        var backupId = createServiceType(token, AcceptanceData.createBackup());
        var psaId = createServiceType(token, AcceptanceData.createPSA());
        var screenShareId = createServiceType(token, AcceptanceData.createScreenShare());

        var userId = createUser(AcceptanceData.registerUser());
        token = login(AcceptanceData.loginUser());

        createDevice(token, AcceptanceData.createDevice(windowsId, 2));
        createDevice(token, AcceptanceData.createDevice(macId, 3));

        createService(token, AcceptanceData.createService(antivirusId));
        createService(token, AcceptanceData.createService(backupId));
        createService(token, AcceptanceData.createService(screenShareId));

        var invoiceId = payBill(token);
        var total = downloadInvoice(token, invoiceId);

        token = login(AcceptanceData.loginRoot(root));
        deleteUser(token, userId);

        deleteServiceType(token, antivirusId);
        deleteServiceType(token, backupId);
        deleteServiceType(token, psaId);
        deleteServiceType(token, screenShareId);

        deleteDeviceType(token, windowsId);
        deleteDeviceType(token, macId);

        assertEquals(71, total);
    }

    private UUID createUser(RegisterUserRequest request) throws Exception {
        var result = mockMvc.perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk())
            .andReturn();

        var content = result.getResponse().getContentAsString();
        var response = objectMapper.readValue(content, UserRegisteredResponse.class);

        return response.getUserId();
    }

    private String login(LoginUserRequest request) throws Exception {
        var result = mockMvc.perform(
            post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andReturn();

        var content = result.getResponse().getContentAsString();
        var response = objectMapper.readValue(content, UserLoggedResponse.class);

        return response.getAccessToken();
    }

    private UUID createDeviceType(String token, rmm.ninjaone.catalog.infrastructure.endpoints.devices.CreateDeviceRequest request) throws Exception {
        var result = mockMvc.perform(
            post("/api/catalog/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("Authorization", "Bearer " + token))
			.andExpect(status().isOk())
            .andReturn();

        var content = result.getResponse().getContentAsString();
        var response = objectMapper.readValue(content, rmm.ninjaone.catalog.infrastructure.endpoints.devices.DeviceResponse.class);

        return response.getId();
    }

    private UUID createServiceType(String token, rmm.ninjaone.catalog.infrastructure.endpoints.services.CreateServiceRequest request) throws  Exception {
        var result = mockMvc.perform(
            post("/api/catalog/services")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("Authorization", "Bearer " + token))
			.andExpect(status().isOk())
            .andReturn();

        var content = result.getResponse().getContentAsString();
        var response = objectMapper.readValue(content, rmm.ninjaone.catalog.infrastructure.endpoints.services.ServiceResponse.class);

        return response.getId();
    }

    private UUID createDevice(String token, rmm.ninjaone.inventory.infrastructure.endpoints.devices.CreateDeviceRequest request) throws Exception {
        var result = mockMvc.perform(
            post("/api/inventory/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("Authorization", "Bearer " + token))
			.andExpect(status().isOk())
            .andReturn();

        var content = result.getResponse().getContentAsString();
        var response = objectMapper.readValue(content, rmm.ninjaone.inventory.infrastructure.endpoints.devices.DeviceResponse.class);

        return response.getId();
    }

    private UUID createService(String token, rmm.ninjaone.inventory.infrastructure.endpoints.services.CreateServiceRequest request) throws Exception {
        var result = mockMvc.perform(
            post("/api/inventory/services")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("Authorization", "Bearer " + token))
			.andExpect(status().isOk())
            .andReturn();

        var content = result.getResponse().getContentAsString();
        var response = objectMapper.readValue(content, rmm.ninjaone.inventory.infrastructure.endpoints.services.ServiceResponse.class);

        return response.getId();
    }

    private UUID payBill(String token) throws Exception {
        var result = mockMvc.perform(
            post("/api/payments/pay")
                .header("Authorization", "Bearer " + token))
			.andExpect(status().isOk())
            .andReturn();

        var content = result.getResponse().getContentAsString();
        var response = objectMapper.readValue(content, PaymentResponse.class);

        return response.getInvoiceId();
    }

    private double downloadInvoice(String token, UUID invoiceId) throws Exception {
        var result = mockMvc.perform(
            get("/api/invoices/" + invoiceId)
                .header("Authorization", "Bearer " + token))
			.andExpect(status().isOk())
            .andReturn();

        var content = result.getResponse().getContentAsString();
        var response = objectMapper.readValue(content, InvoiceResponse.class);

        return response.getTotal();
    }

    private void deleteUser(String token, UUID userId) throws Exception {
        mockMvc.perform(
            delete("/api/users/" + userId)
                .header("Authorization", "Bearer " + token))
			.andExpect(status().isOk());
    }

    private void deleteServiceType(String token, UUID serviceId) throws Exception {
        mockMvc.perform(
            delete("/api/catalog/services/" + serviceId)
                .header("Authorization", "Bearer " + token))
			.andExpect(status().isOk())
            .andReturn();
    }

    private void deleteDeviceType(String token, UUID deviceId) throws Exception {
        mockMvc.perform(
            delete("/api/catalog/devices/" + deviceId)
                .header("Authorization", "Bearer " + token))
			.andExpect(status().isOk())
            .andReturn();
    }
}
