package com.sliit.music.dto;

public final class Constant {

  private Constant() {
  }
  /**
   * CLASS PATHS FOR MODEL MAPPER START
   **/

/**   CLASS PATHS FOR MODEL MAPPER END **/

  /**
   * VALIDATION PATTERN START
   **/

  public static final String MAC_ADDRESS = "[a-zA-Z0-9]{12}";
  public static final String SERIAL_NUMBER = "[a-zA-Z0-9]{12}";
  public static final String TRACE_ID_FIRST_THREE_CHARACTERS = "[a-zA-Z]{3}";
  public static final String TRACE_ID_NUMBER = "[0-9]+";

  /**   VALIDATION PATTERN END  **/

  /**
   * VALIDATION MESSAGES START
   **/

  /**   VALIDATION MESSAGES END **/

  /**
   * API END POINTS START
   **/

  /**   API END POINTS END **/

  /**
   * SUCCESS MESSAGE START
   **/

  /**
   * SUCCESS MESSAGE END
   **/

  /**
   * FAILURE MESSAGE START
   **/

  /**
   * FAILURE MESSAGE END
   **/

  /**
   * EXCEPTION MESSAGE START
   **/
  public static final String DB_KEY_EXCEPTION = "Error occurred while processing the request";
  public static final String INTERNAL_SERVER_ERROR_EXCEPTION = "An internal server error occurred";
  public static final String BAD_SQL_EXCEPTION = "Sql error occurred while processing the request";
  public static final String UNAUTHORIZED_EXCEPTION = "Access Token Invalid or Invalid Credentials";
  public static final String NULL_POINTER_EXCEPTION = "input value is null";

  /**
   *EXCEPTION MESSAGE END
   **/

  /**
   * EXCEPTION MESSAGE START
   **/
  public static final String SUCCESS_CODE = "00";
  public static final String ERROR_CODE = "01";
  public static final String SQL_ERROR_CODE = "02";
  public static final String VALIDATION_ERROR_CODE = "03";



  /**
   *EXCEPTION MESSAGE END
   **/
}
