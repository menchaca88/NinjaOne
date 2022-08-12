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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import rmm.ninjaone.api.data.catalog.CreateDeviceMother;
import rmm.ninjaone.api.endpoints.authentication.LoginUserRequest;
import rmm.ninjaone.api.endpoints.authentication.UserLoggedResponse;
import rmm.ninjaone.api.support.setup.RootAccount;
import rmm.ninjaone.catalog.infrastructure.endpoints.devices.DeviceResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeviceTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RootAccount root;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
	void devices_createDevice_deviceDetails_Returns200() throws Exception {
        var loginRequest = new LoginUserRequest();
        loginRequest.setEmail(root.getEmail());
        loginRequest.setPassword(root.getPassword());

        var loginResult = mockMvc.perform(
            post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk())
            .andReturn();

        var loginContent = loginResult.getResponse().getContentAsString();
        var loginResponse = objectMapper.readValue(loginContent, UserLoggedResponse.class);
        
        var createRequest = CreateDeviceMother.valid();

        var createResult = mockMvc.perform(
            post("/api/catalog/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest))
                .header("Authorization", "Bearer " + loginResponse.getAccessToken()))
			.andExpect(status().isOk())
            .andReturn();

        var createContent = createResult.getResponse().getContentAsString();
        var createResponse = objectMapper.readValue(createContent, DeviceResponse.class);

        var detailsResult = mockMvc.perform(
            get("/api/catalog/devices/" + createResponse.getId())
                .header("Authorization", "Bearer " + loginResponse.getAccessToken()))
            .andExpect(status().isOk())
            .andReturn();

        var detailsContent = detailsResult.getResponse().getContentAsString();
        var detailsResponse = objectMapper.readValue(detailsContent, DeviceResponse.class);
        
        assertEquals(createRequest.getName(), detailsResponse.getName());
        assertTrue(createRequest.getSubscription().getType().startsWith(detailsResponse.getSubscription()));
	}

    @Test
	void devices_createDevice_deleteDevice_Returns200() throws Exception {
        var loginRequest = new LoginUserRequest();
        loginRequest.setEmail(root.getEmail());
        loginRequest.setPassword(root.getPassword());

        var loginResult = mockMvc.perform(
            post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk())
            .andReturn();

        var loginContent = loginResult.getResponse().getContentAsString();
        var loginResponse = objectMapper.readValue(loginContent, UserLoggedResponse.class);
        
        var createRequest = CreateDeviceMother.valid();

        var createResult = mockMvc.perform(
            post("/api/catalog/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest))
                .header("Authorization", "Bearer " + loginResponse.getAccessToken()))
			.andExpect(status().isOk())
            .andReturn();

        var createContent = createResult.getResponse().getContentAsString();
        var createResponse = objectMapper.readValue(createContent, DeviceResponse.class);

        var deleteResult = mockMvc.perform(
            delete("/api/catalog/devices/" + createResponse.getId())
                .header("Authorization", "Bearer " + loginResponse.getAccessToken()))
            .andExpect(status().isOk())
            .andReturn();

        var deleteContent = deleteResult.getResponse().getContentAsString();
        var deleteResponse = objectMapper.readValue(deleteContent, DeviceResponse.class);
        
        assertEquals(createRequest.getName(), deleteResponse.getName());
        assertTrue(createRequest.getSubscription().getType().startsWith(deleteResponse.getSubscription()));
	}
}
