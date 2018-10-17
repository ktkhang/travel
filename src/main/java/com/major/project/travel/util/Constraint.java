package com.major.project.travel.util;

import java.util.HashMap;

/**
 * Created by HUY on 10/16/2018
 * This is an util class to contain ERROR_CODE and ERROR_MESSAGE for each known exception.
 * ERROR_CODE is intended to be mapped with database exception code.
 * ERROR_MESSAGE is intended to be used to support localization.
 */
public final class Constraint {

    //DECLARE CONSTRAINT_CODE here
    //constraint_default_error
    public static final String CONSTRAINT_DEFAULT_ERROR = "constraint_default_error";
    public static final String PLACE_NAME_CONSTRAINT_CODE = "place_name_unique_constraint";
    public static final String PLACE_USER_CONSTRAINT_CODE = "place_user_unique_constraint";
    public static final String USER_NAME_CONSTRAINT_CODE = "user_name_unique_constraint";

    // DECLARE CONSTRAINT_MESSAGES below
    private final static HashMap<String, String> constraintMap = new HashMap<>();

    static {
        constraintMap.put(PLACE_NAME_CONSTRAINT_CODE, "place_name_unique_constraint_duplicated");
        constraintMap.put(PLACE_USER_CONSTRAINT_CODE, "place_user_unique_constraint_duplicated");
        constraintMap.put(USER_NAME_CONSTRAINT_CODE, "user_name_unique_constraint_duplicated");
    }
    public static String getConstraintErrorMessage(String constraintName){
        String result = constraintMap.get(constraintName);
        if(result == null){
            result = CONSTRAINT_DEFAULT_ERROR;
        }
        return result;
    }
}
