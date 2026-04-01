package kr.co.tododeungjang.web.common;

public interface ResponseMessage {
    //HTTP Status 200
    String SUCCESS = "Success.";

    //HTTP Status 400
    String VALIDATION_FAILED = "Validation failed.";
    String DUPLICATE_EMAIL = "Duplicate email.";
    String DUPLICATE_PHONE = "Duplicate Phone.";
    String NOT_EXISTED_USER = "This user does not exist.";
    String NOT_EXISTED_SCHEDULE = "This Schedule does not exist.";
    String NOT_EXISTED_TODO = "This Todo does not exist.";
    String NOT_EXISTED_NOTICE = "This Notice does not exist.";
    String NOT_MATCH_NUMBER = "This number does not match.";

    //HTTP Status 401
    String SIGN_IN_FAIL = "Login information mismatch.";
    String AUTHORIZATION_FAILED = "Authorization Failed.";
    String WRONG_PASSWORD = "Current password is incorrect.";

    //HTTP Status 404
    String FIND_ID_FAIL = "User not found.";

    //HTTP Status 500
    String DATABASE_ERROR = "Database error.";
}
