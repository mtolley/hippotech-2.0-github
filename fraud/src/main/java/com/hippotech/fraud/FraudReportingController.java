package com.hippotech.fraud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class FraudReportingController {
    @Autowired
    FraudCheckRepository fraudCheckRepository;

    @GetMapping(value = "/fraudreporting", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String fraudReporting() {
        List<FraudCheck> fraudChecks = fraudCheckRepository.findAll();

        String page = "<html>\n" + "<header><title>HippoTech Fraud Reporting</title></header>\n"
                + "    <style>"
                + " table, th, td {"
                + " border: 1px solid black;"
                + " border-collapse: collapse;"
                + " }"
                + " th, td {"
                + "    padding: 5px;"
                + " }"
                + " th {"
                + "    text-align: left;"
                + " }"
                + "</style>"
                + "<body>\n"
                + "<h1>Internal - HippoTech Fraud Monitoring</h1>\n";

        page += "<p>This computer system is the property of HippoTech Inc. HippoTech Inc.'s internal computer systems are provided for the processing of data by HippoTech employees and authorized subcontractors only. All data contained on HippoTech Inc. computer systems is owned by the HippoTech Inc. and may, for the purpose of protecting the rights and property of the HippoTech Inc.'s customers and employees be monitored, intercepted, recorded, read, copied, or captured in any manner and disclosed in any manner by authorized personnel.</p>";
        page += "<p>USE OF THIS SYSTEM BY ANY USER, AUTHORIZED OR UNAUTHORIZED, CONSTITUTES CONSENT TO THIS MONITORING, INTERCEPTION, RECORDING, READING, COPYING, OR CAPTURING AND DISCLOSURE.</p>";

        page += "<table> \n";
        page += "<tr> \n";
        page += "  <th>Date</th> \n";
        page += "  <th>Surname</th> \n";
        page += "  <th>Address Line 1</th> \n";
        page += "  <th>Zip</th> \n";
        page += "  <th>Loan Value</th> \n";
        page += "  <th>Action</th> \n";
        page += "</tr> \n";

        for (int i = 0; i < fraudChecks.size(); i++) {
            FraudCheck fraudCheck = fraudChecks.get(i);
            page += "<tr> \n";
            page += "  <td>" + LocalDateTime.now().toString() + "</td> \n";
            page += "  <td>" + fraudCheck.getName() + "</td> \n";
            page += "  <td>" + fraudCheck.getAddress1() + "</td> \n";
            page += "  <td>" + fraudCheck.getZip() + "</td> \n";
            page += "  <td>" + fraudCheck.getLoanValue() + "</td> \n";
            page += "  <td> \n";
            page += "    <button>Accept</button> \n";
            page += "    <button>Block</button> \n";
            page += "    <button>Refer</button> \n";
            page += "  </td> \n";
            page += "</tr> \n";
        }

        page += "</table> \n";

        page += 
          "</body>\n" +
        "</html>";
        return page;
    }
}