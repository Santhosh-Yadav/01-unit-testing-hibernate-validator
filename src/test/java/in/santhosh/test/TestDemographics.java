package in.santhosh.test;

import in.santhosh.entity.Demographics;
import in.santhosh.test.util.TestDataHelper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TestDemographics {


    @Test
    public void testAllFieldsNotNull() {

        Demographics demographics = new Demographics();

        Set<ConstraintViolation<Demographics>> violations = dataValidation(demographics);
        violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);

        List<String> violationMsgs = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        List<String> expectedMsgs = Arrays.asList("Mandatory field is missing - name", "Mandatory field is missing - pan", "Mandatory field is missing - gender");

        assertTrue(violationMsgs.containsAll(expectedMsgs));
        assertEquals(3, violations.size());

    }


    @Test
    public void testAllFieldsValid() {

        Demographics validData = TestDataHelper.getValidData();

        Set<ConstraintViolation<Demographics>> violations = dataValidation(validData);

        violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);

        assertNotNull(violations);
        assertEquals(0, violations.size());

    }

    @ParameterizedTest
    @CsvSource({"ABC123", "BRFPJ19992", "LKDMK@KDMN"})
    public void testDemographicsWithInvalidPan(String pan) {

        Demographics demographics = TestDataHelper.getValidData();
        demographics.setPan(pan);

        Set<ConstraintViolation<Demographics>> violations = dataValidation(demographics);

        violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);

        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().equalsIgnoreCase("Invalid field data - pan"));

    }


    private <T> Set<ConstraintViolation<T>> dataValidation(T request) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<T>> violations = validator.validate(request);

        return violations;
    }


}
