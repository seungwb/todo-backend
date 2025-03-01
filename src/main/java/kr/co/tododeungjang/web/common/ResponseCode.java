package kr.co.tododeungjang.web.common;

public interface ResponseCode {

    //HTTP Status 200
    String SUCCESS = "SU";

    //HTTP Status 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_PHONE = "DP";
    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_SCHEDULE = "NS";
    String NOT_EXISTED_TODO = "NT";

    //HTTP Status 401
    String SIGN_IN_FAIL = "SF";


    //HTTP Status 500
    String DATABASE_ERROR = "DBE";
}
