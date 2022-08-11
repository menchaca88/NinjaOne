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

import rmm.ninjaone.api.data.RegisterUserMother;
import rmm.ninjaone.api.endpoints.authentication.LoginUserRequest;
import rmm.ninjaone.api.endpoints.authentication.UserLoggedResponse;
import rmm.ninjaone.api.endpoints.authentication.UserRegisteredResponse;
import rmm.ninjaone.api.support.setup.RootAccount;
import rmm.ninjaone.identity.infrastructure.endpoints.users.UserResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RootAccount root;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
	void users_createUser_userDetails_Returns200() throws Exception {
        var registerRequest = RegisterUserMother.valid();

		var registerResult = mockMvc.perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
			.andExpect(status().isOk())
            .andReturn();

        var registerContent = registerResult.getResponse().getContentAsString();
        var registerResponse = objectMapper.readValue(registerContent, UserRegisteredResponse.class);

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

        var detailsResult = mockMvc.perform(
            get("/api/users/" + registerResponse.getUserId())
                .header("Authorization", "Bearer " + loginResponse.getAccessToken()))
            .andExpect(status().isOk())
            .andReturn();

        var detailsContent = detailsResult.getResponse().getContentAsString();
        var detailsResponse = objectMapper.readValue(detailsContent, UserResponse.class);
        
        assertEquals(registerRequest.getName(), detailsResponse.getName());
        assertEquals(registerRequest.getEmail(), detailsResponse.getEmail());
	}

    @Test
	void users_createUser_deleteUser_Returns200() throws Exception {
        var registerRequest = RegisterUserMother.valid();

		var registerResult = mockMvc.perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
			.andExpect(status().isOk())
            .andReturn();

        var registerContent = registerResult.getResponse().getContentAsString();
        var registerResponse = objectMapper.readValue(registerContent, UserRegisteredResponse.class);

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

        var deleteResult = mockMvc.perform(
            delete("/api/users/" + registerResponse.getUserId())
                .header("Authorization", "Bearer " + loginResponse.getAccessToken()))
            .andExpect(status().isOk())
            .andReturn();

        var deleteContent = deleteResult.getResponse().getContentAsString();
        var deleteResponse = objectMapper.readValue(deleteContent, UserResponse.class);
        
        assertEquals(registerRequest.getName(), deleteResponse.getName());
        assertEquals(registerRequest.getEmail(), deleteResponse.getEmail());
	}
}
