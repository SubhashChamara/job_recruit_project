package com.recruiter.recruite.service.util;

import com.recruiter.recruite.constants.Gender;
import com.recruiter.recruite.model.NicDetails;
import com.recruiter.recruite.service.exception.BusinessException;
import com.recruiter.recruite.service.exception.BusinessExceptionType;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class NicUtil {


    // Utility method to calculate details from NIC number
    public static NicDetails getDetailsFromNic(String nic) {
        NicDetails details = new NicDetails();

        String yearOfBirthStr;
        int dayOfYear;

        // Determine if it's the old NIC (9 digits) or new NIC (12 digits)
        if (nic.length() == 10 && (nic.endsWith("V") || nic.endsWith("X")||(nic.endsWith("v") || nic.endsWith("x")))) {
            // Old NIC format
            yearOfBirthStr = "19" + nic.substring(0, 2);
            dayOfYear = Integer.parseInt(nic.substring(2, 5));
        } else if (nic.length() == 12) {
            // New NIC format
            yearOfBirthStr = nic.substring(0, 4);
            dayOfYear = Integer.parseInt(nic.substring(4, 7));
        } else {
            throw new BusinessException(BusinessExceptionType.BUSINESS,"Invalid NIC format");
        }

        // Extract the year of birth
        int yearOfBirth = Integer.parseInt(yearOfBirthStr);

        // Determine gender and adjust day of year for females
        if (dayOfYear > 500) {
            details.setGender(Gender.FEMALE);
            dayOfYear -= 500;  // Adjust day of year for females
        } else {
            details.setGender(Gender.MALE);
        }

        // Calculate date of birth (DOB)
        LocalDate birthDate = LocalDate.ofYearDay(yearOfBirth, dayOfYear);
        details.setDob(birthDate);

        // Calculate age
        details.setAge(calculateAge(birthDate));

        return details;
    }

    // Method to calculate age based on birth date
    private static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

}
