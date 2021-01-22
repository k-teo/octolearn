package com.octolearn;


import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;


public class RegisterActivityTest {

    @Test
    public void testEmptyNickname(){
        assertThat(RegisterActivity.validateRegistrationInput("","name@email.com","123","123")).isFalse();
    }

    @Test
    public void testEmptyEmail(){
        assertThat(RegisterActivity.validateRegistrationInput("dams","","123","123")).isFalse();
    }

    @Test
    public void testEmptyPassword(){
        assertThat(RegisterActivity.validateRegistrationInput("dams","name@email.com","","123")).isFalse();
    }

    @Test
    public void testEmptyConfirmPassword(){
        assertThat(RegisterActivity.validateRegistrationInput("dams","name@email.com","123","")).isFalse();
    }

    @Test
    public void testPasswords(){
        assertThat(RegisterActivity.validateRegistrationInput("dams","name@email.com","123567","123")).isFalse();
    }

    @Test
    public void testPasswordDigit(){
        assertThat(RegisterActivity.validateRegistrationInput("dams","name@email.com","password","password")).isFalse();
    }

    @Test
    public void testEmail(){
        assertThat(RegisterActivity.validateRegistrationInput("dams","nameemail.com","123","123")).isFalse();
    }

    @Test
    public void testPasswordLength(){
        assertThat(RegisterActivity.validateRegistrationInput("dams","name@email.com","12","12")).isFalse();
    }
}