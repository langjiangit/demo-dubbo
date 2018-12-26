package com.jd.util;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * wangzhen23
 * 2018/11/22.
 */
public class GridCommandDemo {
    static Logger logger = LoggerFactory.getLogger(GridCommandDemo.class);

    public static void main(String[] args) throws ParseException, IOException {
        logger.info("Running Grid command demo");
        List<Employee> employees = generateSampleEmployeeData();
        executeGridMatrixDemo(employees);
        executeGridObjectListDemo(employees);
    }

    private static void executeGridMatrixDemo(List<Employee> employees) throws IOException {
        List<List<Object>> data = createGridData(employees);
        try (InputStream is = GridCommandDemo.class.getResourceAsStream("/grid_template.xls")) {
            try (OutputStream os = new FileOutputStream("grid_output1.xls")) {
                Context context = new Context();
                context.putVar("headers", Arrays.asList("Name", "Birthday", "Payment"));
                context.putVar("data", data);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        }
    }

    private static void executeGridObjectListDemo(List<Employee> employees) throws IOException {
        try (InputStream is = GridCommandDemo.class.getResourceAsStream("/grid_template.xls")) {
            try (OutputStream os = new FileOutputStream("grid_output2.xls")) {
                Context context = new Context();
                context.putVar("headers", Arrays.asList("Name", "Birthday", "Payment"));
                context.putVar("data", employees);
                JxlsHelper.getInstance().processGridTemplateAtCell(is, os, context, "name,birthDate,payment", "Sheet1!A1");
            }
        }
    }

    private static List<List<Object>> createGridData(List<Employee> employees) {
        List<List<Object>> data = new ArrayList<>();
        for (Employee employee : employees) {
            data.add(convertEmployeeToList(employee));
        }
        return data;
    }

    private static List<Employee> generateSampleEmployeeData() throws ParseException {
        List<Employee> employees = new ArrayList<Employee>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
        employees.add(new Employee("Elsa", dateFormat.parse("1970-Jul-10"), 1500d, 0.15));
        employees.add(new Employee("Oleg", dateFormat.parse("1973-Apr-30"), 2300d, 0.25));
        employees.add(new Employee("Neil", dateFormat.parse("1975-Oct-05"), 2500d, 0.00));
        employees.add(new Employee("Maria", dateFormat.parse("1978-Jan-07"), 1700d, 0.15));
        employees.add(new Employee("John", dateFormat.parse("1969-May-30"), 2800d, 0.20));
        return employees;
    }

    private static List<Object> convertEmployeeToList(Employee employee) {
        List<Object> list = new ArrayList<>();
        list.add(employee.getName());
        list.add(employee.getBirthDate());
        list.add(employee.getPayment());
        return list;
    }
}
