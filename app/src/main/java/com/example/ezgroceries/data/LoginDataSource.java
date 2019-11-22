package com.example.ezgroceries.data;

import com.example.ezgroceries.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String email, String password) {

        try {
            LoggedInUser realUser = new LoggedInUser(username, email, username);
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            "guest", "guest@gmail.com", "Guest");
            if(username.equals(null) && email.equals(null) && password.equals(null))
                return new Result.Success<>(fakeUser);
            return new Result.Success<>(realUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
