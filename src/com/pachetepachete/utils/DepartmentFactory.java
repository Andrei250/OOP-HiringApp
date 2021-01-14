package com.pachetepachete.utils;

import com.pachetepachete.Models.*;

/*
    Factory pentru departamente.
 */
public class DepartmentFactory {
    public Department getDepartment(String departmentType) {
        if (departmentType == null) {
            return null;
        }

        if (departmentType.equalsIgnoreCase("IT")) {
            return new IT(0.0);
        } else if (departmentType.equalsIgnoreCase("MANAGEMENT")) {
            return new Management(0.16);
        } else if (departmentType.equalsIgnoreCase("MARKETING")) {
            return new Marketing(0.1);
        } else if (departmentType.equalsIgnoreCase("FINANCE")) {
            return new Finance();
        }

        return null;
    }
}
