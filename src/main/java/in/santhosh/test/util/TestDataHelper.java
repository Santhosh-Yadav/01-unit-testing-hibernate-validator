package in.santhosh.test.util;

import in.santhosh.entity.Demographics;

public class TestDataHelper {

    public static Demographics getValidData(){

        Demographics demographics = new Demographics();

        demographics.setName(DemographicConstants.NAME);
        demographics.setPan(DemographicConstants.PAN);
        demographics.setGender(DemographicConstants.GENDER);

        return  demographics;

    }

}
