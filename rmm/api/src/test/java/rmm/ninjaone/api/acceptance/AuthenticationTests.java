package rmm.ninjaone.api.acceptance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import rmm.ninjaone.api.data.users.LoginUserMother;
import rmm.ninjaone.api.data.users.RegisterUserMother;
import rmm.ninjaone.api.endpoints.authentication.LoginUserRequest;
import rmm.ninjaone.api.endpoints.authentication.UserLoggedResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
	void auth_registerUser_loginUser_testEndpoint_Returns200() throws Exception {
        var registerRequest = RegisterUserMother.valid();

        var loginRequest = new LoginUserRequest();
        loginRequest.setEmail(registerRequest.getEmail());
        loginRequest.setPassword(registerRequest.getPassword());

		mockMvc.perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
			.andExpect(status().isOk());

        var loginResult = mockMvc.perform(
            post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk())
            .andReturn();

        var loginContent = loginResult.getResponse().getContentAsString();
        var loginResponse = objectMapper.readValue(loginContent, UserLoggedResponse.class);

        var testResult = mockMvc.perform(
            get("/api/test")
                .header("Authorization", "Bearer " + loginResponse.getAccessToken()))
            .andExpect(status().isOk())
            .andReturn();

        var testResponse = testResult.getResponse().getContentAsString();
        var expectedResponse = "Hello " + registerRequest.getEmail();

        assertEquals(expectedResponse, testResponse);
	}

    @Test
	void auth_loginNonExistingUser_Returns401() throws Exception {
        var request = LoginUserMother.valid();

		mockMvc.perform(
            post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isUnauthorized());
	}

    @Test
	void auth_testEndpoint_Returns401() throws Exception {
		mockMvc.perform(
            get("/api/test"))
            .andExpect(status().isUnauthorized());
	}
}
